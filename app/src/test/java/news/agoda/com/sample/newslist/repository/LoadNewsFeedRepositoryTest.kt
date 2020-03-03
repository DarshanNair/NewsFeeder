package news.agoda.com.sample.newslist.repository

import news.agoda.com.sample.core.network.api.AgodaApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class LoadNewsFeedRepositoryTest {

    private lateinit var subject: LoadNewsFeedRepository

    @Mock
    private lateinit var mockAgodaApi: AgodaApi

    @Before
    fun setUp() {
        subject = LoadNewsFeedRepositoryImpl(mockAgodaApi)
    }

    @Test
    fun `Load News Feed`() {
        // WHEN
        subject.getNewsFeed()

        // THEN
        then(mockAgodaApi).should().getNewsFeed()
    }

}
