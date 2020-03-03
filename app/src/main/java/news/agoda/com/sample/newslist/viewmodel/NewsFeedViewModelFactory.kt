package news.agoda.com.sample.newslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import news.agoda.com.sample.newslist.domain.LoadNewsFeedUseCase
import javax.inject.Inject

class NewsFeedViewModelFactory @Inject constructor(
        private val loadNewsFeedUseCase: LoadNewsFeedUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
            NewsFeedViewModelImpl(
                    loadNewsFeedUseCase
            ) as T

}