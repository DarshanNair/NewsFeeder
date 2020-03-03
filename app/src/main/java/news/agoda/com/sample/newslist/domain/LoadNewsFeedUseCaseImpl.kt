package news.agoda.com.sample.newslist.domain

import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import news.agoda.com.sample.core.domain.BaseUseCase
import news.agoda.com.sample.core.injection.qualifiers.ForIoThread
import news.agoda.com.sample.core.injection.qualifiers.ForMainThread
import news.agoda.com.sample.core.network.model.NewsFeed
import news.agoda.com.sample.newslist.domain.LoadNewsFeedUseCase.Callback
import news.agoda.com.sample.newslist.repository.LoadNewsFeedRepository
import javax.inject.Inject

class LoadNewsFeedUseCaseImpl @Inject constructor(
        compositeDisposable: CompositeDisposable,
        private val loadNewsFeedRepository: LoadNewsFeedRepository,
        @ForIoThread private val ioScheduler: Scheduler,
        @ForMainThread private val mainScheduler: Scheduler
) : BaseUseCase(compositeDisposable), LoadNewsFeedUseCase {

    private var callback: Callback? = null

    override fun execute() {
        trackDisposable(
                loadNewsFeedRepository.getNewsFeed()
                        .subscribeOn(ioScheduler)
                        .observeOn(mainScheduler)
                        .subscribe(::onSuccess, ::onError)
        )
    }

    override fun setCallback(callback: Callback) {
        this.callback = callback
    }

    private fun onSuccess(newsFeed: NewsFeed) {
        callback?.onLoadNewsFeedSuccess(newsFeed)
    }

    private fun onError(throwable: Throwable) {
        callback?.onLoadNewsFeedError(throwable)
    }

    override fun cleanup() {
        callback = null
        super.cleanup()
    }

}