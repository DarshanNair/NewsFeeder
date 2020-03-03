package news.agoda.com.sample.core.network.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Nullable;

@AutoValue
public abstract class NewsFeed implements Parcelable {

    public static Builder builder() {
        return new AutoValue_NewsFeed.Builder();
    }

    @SerializedName("results")
    public abstract List<Result> getResults();

    @AutoValue.Builder
    public static abstract class Builder {

        public abstract Builder results(List<Result> claims);

        public abstract NewsFeed build();
    }

    @AutoValue
    public static abstract class Result implements Parcelable {

        public static Builder builder() {
            return new AutoValue_NewsFeed_Result.Builder();
        }

        @Nullable
        @SerializedName("title")
        public abstract String getTitle();

        @Nullable
        @SerializedName("abstract")
        public abstract String getAbstraction();

        @Nullable
        @SerializedName("url")
        public abstract String getUrl();

        @Nullable
        @SerializedName("byline")
        public abstract String getByline();

        @Nullable
        @SerializedName("published_date")
        public abstract String getPublishedDate();

        @Nullable
        @SerializedName("multimedia")
        public abstract List<Multimedia> getMultimedia();

        @AutoValue.Builder
        public static abstract class Builder {

            public abstract Builder title(String title);

            public abstract Builder abstraction(String abstraction);

            public abstract Builder url(String url);

            public abstract Builder byline(String byline);

            public abstract Builder publishedDate(String published_date);

            public abstract Builder multimedia(List<Multimedia> multimedia);

            public abstract Result build();
        }

    }

    @AutoValue
    public static abstract class Multimedia implements Parcelable {

        public static Builder builder() {
            return new AutoValue_NewsFeed_Multimedia.Builder();
        }

        @Nullable
        @SerializedName("url")
        public abstract String getUrl();

        @Nullable
        @SerializedName("format")
        public abstract String getFormat();

        @SerializedName("height")
        public abstract int getHeight();

        @SerializedName("width")
        public abstract int getWidth();

        @Nullable
        @SerializedName("type")
        public abstract String getType();

        @Nullable
        @SerializedName("subtype")
        public abstract String getSubtype();

        @Nullable
        @SerializedName("caption")
        public abstract String getCaption();

        @Nullable
        @SerializedName("copyright")
        public abstract String getCopyright();

        @AutoValue.Builder
        public static abstract class Builder {

            public abstract Builder url(String url);

            public abstract Builder format(String format);

            public abstract Builder height(int height);

            public abstract Builder width(int width);

            public abstract Builder type(String type);

            public abstract Builder subtype(String subtype);

            public abstract Builder caption(String caption);

            public abstract Builder copyright(String copyright);

            public abstract Multimedia build();
        }

    }

}