package news.agoda.com.sample.core.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import news.agoda.com.sample.core.NewsReaderApplication
import news.agoda.com.sample.core.injection.scopes.PerApplication
import news.agoda.com.sample.core.network.api.injection.AgodaApiModule
import news.agoda.com.sample.view.injection.NewsFeedActivityBuilderModule

@PerApplication
@Component(modules = [
    BaseModule::class,
    AgodaApiModule::class,
    NewsFeedActivityBuilderModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: NewsReaderApplication)

}