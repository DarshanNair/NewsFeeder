package news.agoda.com.sample.newslist.repository.injection

import dagger.Module
import dagger.Provides
import news.agoda.com.sample.newslist.repository.LoadNewsFeedRepository
import news.agoda.com.sample.newslist.repository.LoadNewsFeedRepositoryImpl

@Module
class LoadNewsFeedRepositoryModule {

    @Provides
    fun provideLoadNewsFeedRepository(repository: LoadNewsFeedRepositoryImpl): LoadNewsFeedRepository =
            repository

}