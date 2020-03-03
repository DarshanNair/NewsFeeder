package news.agoda.com.sample.newslist.repository

import news.agoda.com.sample.core.network.api.AgodaApi
import javax.inject.Inject

class LoadNewsFeedRepositoryImpl @Inject constructor(
        private val agodaApi: AgodaApi
) : LoadNewsFeedRepository {

    override fun getNewsFeed() = agodaApi.getNewsFeed()

}