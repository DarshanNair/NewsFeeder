package news.agoda.com.sample.view.injection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import news.agoda.com.sample.core.injection.scopes.PerActivity
import news.agoda.com.sample.view.NewsFeedActivity

@Module
abstract class NewsFeedActivityBuilderModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [NewsFeedActivityModule::class])
    abstract fun bindNewsFeedActivity(): NewsFeedActivity

}