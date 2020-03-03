package news.agoda.com.sample.newslist.domain

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import news.agoda.com.sample.core.network.model.NewsFeed
import news.agoda.com.sample.newslist.repository.LoadNewsFeedRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class LoadNewsFeedUseCaseTest {

    private lateinit var subject: LoadNewsFeedUseCase

    @Mock
    private lateinit var mockCallback: LoadNewsFeedUseCase.Callback
    @Mock
    private lateinit var mockCompositeDisposable: CompositeDisposable
    @Mock
    private lateinit var mockLoadNewsFeedRepository: LoadNewsFeedRepository
    @Mock
    private lateinit var mockNetworkError: IOException
    @Mock
    private lateinit var mockNewsFeed: NewsFeed

    @Before
    fun setUp() {
        subject = LoadNewsFeedUseCaseImpl(
            mockCompositeDisposable,
            mockLoadNewsFeedRepository,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )

        subject.setCallback(mockCallback)
    }

    @Test
    fun `Success - Fetch News Feed`() {
        // GIVEN
        given(mockLoadNewsFeedRepository.getNewsFeed()).willReturn(Single.just(mockNewsFeed))

        // WHEN
        subject.execute()

        // THEN
        then(mockCallback).should().onLoadNewsFeedSuccess(mockNewsFeed)
        then(mockCallback).shouldHaveNoMoreInteractions()
    }

    @Test
    fun `Network Error - Fetch News Feed`() {
        // GIVEN
        given(mockLoadNewsFeedRepository.getNewsFeed()).willReturn(Single.error(mockNetworkError))

        // WHEN
        subject.execute()

        // THEN
        then(mockCallback).should().onLoadNewsFeedError(mockNetworkError)
        then(mockCallback).shouldHaveNoMoreInteractions()
    }

    @Test
    fun `cleanup on destroy`() {
        // WHEN
        subject.cleanup()

        // THEN
        then(mockCompositeDisposable).should().clear()
    }

}
