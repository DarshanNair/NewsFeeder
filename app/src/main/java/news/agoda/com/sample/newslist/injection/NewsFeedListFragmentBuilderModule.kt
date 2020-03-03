package news.agoda.com.sample.newslist.injection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import news.agoda.com.sample.core.injection.scopes.PerFragment
import news.agoda.com.sample.newslist.view.NewsFeedListFragment

@Module
abstract class NewsFeedListFragmentBuilderModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [NewsFeedListFragmentModule::class])
    abstract fun bindNewsFeedListFragment(): NewsFeedListFragment

}