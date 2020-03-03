package news.agoda.com.sample.core.injection

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import news.agoda.com.sample.core.injection.qualifiers.ForApplication
import news.agoda.com.sample.core.injection.qualifiers.ForIoThread
import news.agoda.com.sample.core.injection.qualifiers.ForMainThread
import news.agoda.com.sample.core.injection.scopes.PerApplication

@Module
class BaseModule {

    @Provides
    @ForApplication
    fun provideContext(application: Application): Context = application

    @Provides
    @ForApplication
    fun provideApplication(application: Application): Application = application

    @Provides
    @PerApplication
    @ForIoThread
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Provides
    @PerApplication
    @ForMainThread
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

}