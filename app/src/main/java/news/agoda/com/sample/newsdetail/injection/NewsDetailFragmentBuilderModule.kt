package news.agoda.com.sample.newsdetail.injection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import news.agoda.com.sample.core.injection.scopes.PerFragment
import news.agoda.com.sample.newsdetail.view.NewsDetailFragment

@Module
abstract class NewsDetailFragmentBuilderModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [NewsDetailFragmentModule::class])
    abstract fun bindNewsDetailFragment(): NewsDetailFragment

}