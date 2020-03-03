package news.agoda.com.sample.view.injection

import dagger.Module
import news.agoda.com.sample.newsdetail.injection.NewsDetailFragmentBuilderModule
import news.agoda.com.sample.newslist.injection.NewsFeedListFragmentBuilderModule

@Module(includes = [
    NewsFeedListFragmentBuilderModule::class,
    NewsDetailFragmentBuilderModule::class
])
class NewsFeedActivityModule