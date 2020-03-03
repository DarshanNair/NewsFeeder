package news.agoda.com.sample.core.network.api.injection

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import news.agoda.com.sample.core.injection.qualifiers.ForApplication
import news.agoda.com.sample.core.injection.scopes.PerApplication
import news.agoda.com.sample.core.network.api.AgodaApi
import news.agoda.com.sample.core.network.model.NewsFeed
import news.agoda.com.sample.core.network.parser.NewsFeedParser
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class AgodaApiModule {

    companion object {
        private const val CACHE_SIZE = 50 * 1024 * 1024
    }

    @Provides
    @PerApplication
    fun provideOkHttpClient(okHttpBuilder: OkHttpClient.Builder): OkHttpClient {
        return okHttpBuilder.build()
    }

    @Provides
    @PerApplication
    fun provideAgodaApi(
            retrofitBuilder: Retrofit.Builder,
            client: OkHttpClient,
            gson: Gson
    ): AgodaApi {
        return retrofitBuilder.client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.myjson.com/bins/")
                .build()
                .create(AgodaApi::class.java)
    }

    @Provides
    @PerApplication
    fun provideAgodaApiGson(
            newsFeedParser: NewsFeedParser
    ): Gson {
        return GsonBuilder()
                .registerTypeAdapter(NewsFeed::class.java, newsFeedParser)
                .create()
    }

    @Provides
    @PerApplication
    fun provideOkHttpClientBuilder(cache: Cache): OkHttpClient.Builder {
        return OkHttpClient().newBuilder()
                .followRedirects(true)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true)
                .cache(cache)
                .connectTimeout(10L, TimeUnit.SECONDS)
                .readTimeout(30L, TimeUnit.SECONDS)
    }

    @Provides
    @PerApplication
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Provides
    @PerApplication
    fun provideCache(@ForApplication context: Context): Cache =
            Cache(context.cacheDir, CACHE_SIZE.toLong())

}