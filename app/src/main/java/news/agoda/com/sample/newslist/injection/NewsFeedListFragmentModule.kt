package news.agoda.com.sample.newslist.injection

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import news.agoda.com.sample.core.injection.qualifiers.ForFragment
import news.agoda.com.sample.core.injection.scopes.PerFragment
import news.agoda.com.sample.newslist.domain.injection.LoadNewsFeedUseCaseModule
import news.agoda.com.sample.newslist.view.NewsFeedListFragment
import news.agoda.com.sample.newslist.viewmodel.NewsFeedViewModel
import news.agoda.com.sample.newslist.viewmodel.NewsFeedViewModelFactory

@Module(includes = [
    LoadNewsFeedUseCaseModule::class
])
class NewsFeedListFragmentModule {

    @Provides
    @PerFragment
    @ForFragment
    fun provideContext(fragment: NewsFeedListFragment): Context = fragment.requireContext()

    @Provides
    @PerFragment
    internal fun provideNewsFeedViewModel(
            fragment: NewsFeedListFragment,
            factory: NewsFeedViewModelFactory
    ): NewsFeedViewModel = ViewModelProviders.of(fragment, factory).get(NewsFeedViewModel::class.java)

}