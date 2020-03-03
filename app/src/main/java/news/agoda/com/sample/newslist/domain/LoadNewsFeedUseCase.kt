package news.agoda.com.sample.newslist.domain

import news.agoda.com.sample.core.domain.UseCase
import news.agoda.com.sample.core.network.model.NewsFeed

interface LoadNewsFeedUseCase : UseCase {

    fun execute()

    fun setCallback(callback: Callback)

    interface Callback {
        fun onLoadNewsFeedSuccess(newsFeed: NewsFeed)
        fun onLoadNewsFeedError(throwable: Throwable)
    }

}