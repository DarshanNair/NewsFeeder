package news.agoda.com.sample.core

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import news.agoda.com.sample.core.injection.DaggerAppComponent
import javax.inject.Inject

open class NewsReaderApplication : Application(), HasActivityInjector/*, HasSupportFragmentInjector*/ {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    /*@Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>*/

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

    /*override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector*/

}
