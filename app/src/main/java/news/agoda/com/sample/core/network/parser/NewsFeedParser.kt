package news.agoda.com.sample.core.network.parser


import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import news.agoda.com.sample.core.network.model.NewsFeed
import java.util.*
import javax.inject.Inject

class NewsFeedParser @Inject constructor() : TypeAdapter<NewsFeed>() {

    companion object {
        private const val KEY_RESULTS = "results"

        private const val KEY_RESULT_TITLE = "title"
        private const val KEY_RESULT_ABSTRACT = "abstract"
        private const val KEY_RESULT_URL = "url"
        private const val KEY_RESULT_BYLINE = "byline"
        private const val KEY_RESULT_PUBLISHED_DATE = "published_date"
        private const val KEY_RESULT_MULTIMEDIA = "multimedia"

        private const val KEY_RESULT_MULTIMEDIA_URL = "url"
        private const val KEY_RESULT_MULTIMEDIA_FORMAT = "format"
        private const val KEY_RESULT_MULTIMEDIA_HEIGHT = "height"
        private const val KEY_RESULT_MULTIMEDIA_WIDTH = "width"
        private const val KEY_RESULT_MULTIMEDIA_TYPE = "type"
        private const val KEY_RESULT_MULTIMEDIA_SUBTYPE = "subtype"
        private const val KEY_RESULT_MULTIMEDIA_CAPTION = "caption"
        private const val KEY_RESULT_MULTIMEDIA_COPYRIGHT = "copyright"
    }

    override fun read(jr: JsonReader): NewsFeed {
        val builder = NewsFeed.builder()
        jr.beginObject()

        while (jr.hasNext()) {
            when (jr.nextName()) {
                KEY_RESULTS -> builder.results(parseResults(jr))
                else -> jr.skipValue()
            }
        }
        jr.endObject()

        return builder.build()
    }

    private fun parseResults(jr: JsonReader): List<NewsFeed.Result> {
        val result = ArrayList<NewsFeed.Result>()
        jr.beginArray()

        while (jr.hasNext()) {
            jr.beginObject()
            val resultBuilder = NewsFeed.Result.builder()

            while (jr.hasNext()) {
                when (jr.nextName()) {
                    KEY_RESULT_TITLE -> resultBuilder.title(readString(jr))
                    KEY_RESULT_ABSTRACT -> resultBuilder.abstraction(readString(jr))
                    KEY_RESULT_URL -> resultBuilder.url(readString(jr))
                    KEY_RESULT_BYLINE -> resultBuilder.byline(readString(jr))
                    KEY_RESULT_PUBLISHED_DATE -> resultBuilder.publishedDate(readString(jr))
                    KEY_RESULT_MULTIMEDIA -> resultBuilder.multimedia(parseMultimedia(jr))
                    else -> jr.skipValue()
                }
            }
            jr.endObject()
            result.add(resultBuilder.build())
        }

        jr.endArray()

        return result
    }

    private fun parseMultimedia(jr: JsonReader): List<NewsFeed.Multimedia> {
        val multimedia = mutableListOf<NewsFeed.Multimedia>()

        try {
            jr.beginArray()
            while (jr.hasNext()) {
                jr.beginObject()
                val multimediaBuilder = NewsFeed.Multimedia.builder()

                while (jr.hasNext()) {
                    when (jr.nextName()) {
                        KEY_RESULT_MULTIMEDIA_URL -> multimediaBuilder.url(readString(jr))
                        KEY_RESULT_MULTIMEDIA_FORMAT -> multimediaBuilder.format(readString(jr))
                        KEY_RESULT_MULTIMEDIA_HEIGHT -> multimediaBuilder.height(readInteger(jr))
                        KEY_RESULT_MULTIMEDIA_WIDTH -> multimediaBuilder.width(readInteger(jr))
                        KEY_RESULT_MULTIMEDIA_TYPE -> multimediaBuilder.type(readString(jr))
                        KEY_RESULT_MULTIMEDIA_SUBTYPE -> multimediaBuilder.subtype(readString(jr))
                        KEY_RESULT_MULTIMEDIA_CAPTION -> multimediaBuilder.caption(readString(jr))
                        KEY_RESULT_MULTIMEDIA_COPYRIGHT -> multimediaBuilder.copyright(readString(jr))
                        else -> jr.skipValue()
                    }
                }
                jr.endObject()
                multimedia.add(multimediaBuilder.build())
            }
            jr.endArray()
        } catch (e: Exception) {
            //its string value
            readString(jr)
        }

        return multimedia
    }

    private fun readString(reader: JsonReader): String? {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return null
        }

        return reader.nextString()
    }

    private fun readInteger(reader: JsonReader): Int {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return Integer.MIN_VALUE
        }

        return reader.nextInt()
    }

    override fun write(out: JsonWriter?, value: NewsFeed?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}