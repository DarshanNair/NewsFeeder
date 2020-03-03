package news.agoda.com.sample.newslist.domain.injection

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import news.agoda.com.sample.newslist.domain.LoadNewsFeedUseCase
import news.agoda.com.sample.newslist.domain.LoadNewsFeedUseCaseImpl
import news.agoda.com.sample.newslist.repository.injection.LoadNewsFeedRepositoryModule

@Module(
        includes = [
            LoadNewsFeedRepositoryModule::class
        ]
)
class LoadNewsFeedUseCaseModule {

    @Provides
    fun provideLoadNewsFeedUseCase(usecase: LoadNewsFeedUseCaseImpl): LoadNewsFeedUseCase =
            usecase

    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

}
