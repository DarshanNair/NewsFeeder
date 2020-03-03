package news.agoda.com.sample.newslist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_news_reader_list.*
import kotlinx.android.synthetic.main.view_news_reader_list_loaded.*
import news.agoda.com.sample.R
import news.agoda.com.sample.core.network.model.NewsFeed
import news.agoda.com.sample.newsdetail.view.NewsDetailFragment
import news.agoda.com.sample.newslist.view.adapter.NewsListAdapter
import news.agoda.com.sample.newslist.viewmodel.NewsFeedViewModel
import news.agoda.com.sample.newslist.viewmodel.NewsFeedViewModel.State
import javax.inject.Inject

class NewsFeedListFragment : Fragment() {

    @Inject
    lateinit var topTrendingListViewModel: NewsFeedViewModel

    enum class UIState {
        LOADING,
        LOADED,
        ERROR
    }

    interface OnHeadlineSelectedListener {
        fun onArticleSelected(result: NewsFeed.Result)
    }

    lateinit var callback: OnHeadlineSelectedListener

    fun setOnHeadlineSelectedListener(callback: OnHeadlineSelectedListener) {
        this.callback = callback
    }

    companion object {
        fun newInstance() = NewsFeedListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.fragment_news_reader_list, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Fresco.initialize(requireContext())
        topTrendingListViewModel.apply {
            state().observe(
                    this@NewsFeedListFragment,
                    Observer { it?.let { onTopTrendingUsersLoaded(it) } })
            loadNewsFeed()
        }
    }

    private fun onTopTrendingUsersLoaded(state: State) {
        when (state) {
            State.Loading -> view_flipper_news_reader.displayedChild = UIState.LOADING.ordinal
            is State.Success -> {
                view_flipper_news_reader.displayedChild = UIState.LOADED.ordinal
                setListContent(state.results)
            }
            State.Error -> view_flipper_news_reader.displayedChild = UIState.ERROR.ordinal
        }
    }

    private fun setListContent(results: List<NewsFeed.Result>) {
        val adapter = NewsListAdapter(requireContext(), R.layout.list_item_news, results)
        news_feed_list.adapter = adapter

        news_feed_list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            callback.onArticleSelected(results[position])
        }
    }

}
