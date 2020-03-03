package news.agoda.com.sample.core.network.api

import io.reactivex.Single
import news.agoda.com.sample.core.network.model.NewsFeed
import retrofit2.http.GET

interface AgodaApi {

    @GET("nl6jh")
    fun getNewsFeed(): Single<NewsFeed>

}