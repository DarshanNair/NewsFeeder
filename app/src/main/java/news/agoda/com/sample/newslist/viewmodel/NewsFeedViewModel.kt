package news.agoda.com.sample.newslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import news.agoda.com.sample.core.network.model.NewsFeed
import news.agoda.com.sample.newslist.domain.LoadNewsFeedUseCase

abstract class NewsFeedViewModel : ViewModel(), LoadNewsFeedUseCase.Callback {

    sealed class State {
        object Loading : State()
        data class Success(val results: List<NewsFeed.Result>) : State()
        object Error : State()
    }

    abstract fun state(): LiveData<State>

    abstract fun loadNewsFeed()

}