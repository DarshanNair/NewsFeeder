package news.agoda.com.sample.newsdetail.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.request.ImageRequest
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_detail.*
import news.agoda.com.sample.R
import news.agoda.com.sample.core.network.model.NewsFeed

class NewsDetailFragment : Fragment() {

    companion object {
        private const val KEY_NEWS_FEED = "KEY_NEWS_FEED"

        fun newInstance(result: NewsFeed.Result) = NewsDetailFragment().apply {
            arguments = bundleOf(KEY_NEWS_FEED to result)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.fragment_detail, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        drawUI()
    }

    private lateinit var newsFeedResult: NewsFeed.Result

    private fun drawUI() {
        arguments?.let { it ->
            newsFeedResult = it.getParcelable(KEY_NEWS_FEED)

            var imageURL = ""
            newsFeedResult.multimedia?.let {
                if (it.size > 0) {
                    imageURL = it[0]?.url ?: ""
                }
            }

            title_view.text = newsFeedResult.title ?: ""
            summary_content.text = newsFeedResult.abstraction ?: ""

            val draweeController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(ImageRequest.fromUri(Uri.parse(imageURL)))
                    .setOldController(news_image.controller).build()
            news_image.controller = draweeController
        }

        full_story_link.setOnClickListener { onFullStoryClicked() }

    }

    private fun onFullStoryClicked() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(newsFeedResult.url)
        startActivity(intent)
    }
}
