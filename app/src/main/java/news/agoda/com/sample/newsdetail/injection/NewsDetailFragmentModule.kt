package news.agoda.com.sample.newsdetail.injection

import android.content.Context
import dagger.Module
import dagger.Provides
import news.agoda.com.sample.core.injection.qualifiers.ForFragment
import news.agoda.com.sample.core.injection.scopes.PerFragment
import news.agoda.com.sample.newsdetail.view.NewsDetailFragment

@Module
class NewsDetailFragmentModule {

    @Provides
    @PerFragment
    @ForFragment
    fun provideContext(fragment: NewsDetailFragment): Context = fragment.requireContext()

}