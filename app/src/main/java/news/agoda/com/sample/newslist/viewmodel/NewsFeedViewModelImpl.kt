package news.agoda.com.sample.newslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import news.agoda.com.sample.core.network.model.NewsFeed
import news.agoda.com.sample.newslist.domain.LoadNewsFeedUseCase
import javax.inject.Inject

class NewsFeedViewModelImpl @Inject internal constructor(
        private val loadNewsFeedUseCase: LoadNewsFeedUseCase
) : NewsFeedViewModel() {

    private val stateLiveData = MutableLiveData<NewsFeedViewModel.State>()

    init {
        loadNewsFeedUseCase.setCallback(this)
    }

    override fun state(): LiveData<State> = stateLiveData

    override fun loadNewsFeed() {
        stateLiveData.value = State.Loading
        loadNewsFeedUseCase.execute()
    }

    override fun onLoadNewsFeedSuccess(newsFeed: NewsFeed) {
        stateLiveData.value = State.Success(newsFeed.results)
    }

    override fun onLoadNewsFeedError(throwable: Throwable) {
        stateLiveData.value = State.Error
    }

    public override fun onCleared() {
        super.onCleared()
        loadNewsFeedUseCase.cleanup()
    }

}