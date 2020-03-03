package news.agoda.com.sample.newslist.repository

import io.reactivex.Single
import news.agoda.com.sample.core.network.model.NewsFeed

interface LoadNewsFeedRepository {

    fun getNewsFeed(): Single<NewsFeed>

}