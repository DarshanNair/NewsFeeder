package news.agoda.com.sample.newslist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import news.agoda.com.sample.core.network.model.NewsFeed
import news.agoda.com.sample.newslist.domain.LoadNewsFeedUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.Mockito.reset
import org.mockito.junit.MockitoJUnitRunner

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class NewsFeedViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var subject: NewsFeedViewModelImpl

    @Mock
    private lateinit var mockLoadNewsFeedUseCase: LoadNewsFeedUseCase
    @Mock
    private lateinit var mockObserver: Observer<NewsFeedViewModel.State>
    @Mock
    private lateinit var mockThrowable: Throwable
    @Mock
    private lateinit var mockNewsFeed: NewsFeed
    @Mock
    private lateinit var mockNewsFeedResult: List<NewsFeed.Result>


    @Before
    fun setUp() {
        subject = NewsFeedViewModelImpl(
            mockLoadNewsFeedUseCase
        )
        subject.state().observeForever(mockObserver)
    }

    @Test
    fun `Load News Feed`() {
        // GIVEN
        reset(mockLoadNewsFeedUseCase)

        // WHEN
        subject.loadNewsFeed()

        // THEN
        thenObserverShouldReceiveCorrectStates(NewsFeedViewModel.State.Loading)
        then(mockLoadNewsFeedUseCase).should().execute()
        then(mockLoadNewsFeedUseCase).shouldHaveNoMoreInteractions()
    }

    @Test
    fun `On Load News Feed - Success`() {
        // GIVEN
        given(mockNewsFeed.results).willReturn(mockNewsFeedResult)

        // WHEN
        subject.onLoadNewsFeedSuccess(mockNewsFeed)

        // THEN
        thenObserverShouldReceiveCorrectStates(NewsFeedViewModel.State.Success(mockNewsFeedResult))
    }

    @Test
    fun `On Load News Feed - Error`() {
        // WHEN
        subject.onLoadNewsFeedError(mockThrowable)

        // THEN
        thenObserverShouldReceiveCorrectStates(NewsFeedViewModel.State.Error)
    }

    @Test
    fun `Clean Up on destroy`() {
        // GIVEN
        reset(mockLoadNewsFeedUseCase)

        // WHEN
        subject.onCleared()

        // THEN
        then(mockLoadNewsFeedUseCase).should().cleanup()
        then(mockLoadNewsFeedUseCase).shouldHaveNoMoreInteractions()
    }

    private fun thenObserverShouldReceiveCorrectStates(vararg expected: NewsFeedViewModel.State) {
        expected.forEach { then(mockObserver).should().onChanged(it) }
        then(mockObserver).shouldHaveNoMoreInteractions()
    }

}
