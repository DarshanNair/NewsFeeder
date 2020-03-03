package news.agoda.com.sample.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_news_reader.*
import news.agoda.com.sample.R
import news.agoda.com.sample.core.network.model.NewsFeed
import news.agoda.com.sample.newsdetail.view.NewsDetailFragment
import news.agoda.com.sample.newslist.view.NewsFeedListFragment
import javax.inject.Inject

class NewsFeedActivity : AppCompatActivity(), HasSupportFragmentInjector, NewsFeedListFragment.OnHeadlineSelectedListener {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_news_reader)

        supportFragmentManager.commit {
            replace(R.id.fragment_container, NewsFeedListFragment.newInstance())
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is NewsFeedListFragment) {
            fragment.setOnHeadlineSelectedListener(this)
        }
    }

    override fun onArticleSelected(result: NewsFeed.Result) {
        if(fragment_container_right != null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container_right, NewsDetailFragment.newInstance(result))
            }
        } else {
            supportFragmentManager.commit {
                addToBackStack(null)
                add(R.id.fragment_container, NewsDetailFragment.newInstance(result))
            }
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? = fragmentDispatchingAndroidInjector

}
