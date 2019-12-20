package mocha.yusuf.jetpack3.Data.Source.Local.Entity;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Movies")
public class MovieEntity implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private long id;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private String poster_path;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title;

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    private String release_date;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private String overview;

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    private String original_language;

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private String vote_average;

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    private String backdrop_path;

    @ColumnInfo(name = "favorite")
    private boolean favorite = false;

    public MovieEntity(long id, String poster_path, String title, String release_date, String overview, String original_language, String vote_average, String backdrop_path, Boolean favorite) {
        this.id = id;
        this.poster_path = poster_path;
        this.title = title;
        this.release_date = release_date;
        this.overview = overview;
        this.original_language = original_language;
        this.vote_average = vote_average;
        this.backdrop_path = backdrop_path;
        if (favorite != null)
            this.favorite = favorite;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getTitle() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.poster_path);
        dest.writeString(this.title);
        dest.writeString(this.release_date);
        dest.writeString(this.overview);
        dest.writeString(this.original_language);
        dest.writeString(this.vote_average);
        dest.writeString(this.backdrop_path);
        dest.writeByte(this.favorite ? (byte) 1 : (byte) 0);
    }

    public MovieEntity(Parcel in) {
        this.id = in.readLong();
        this.poster_path = in.readString();
        this.title = in.readString();
        this.release_date = in.readString();
        this.overview = in.readString();
        this.original_language = in.readString();
        this.vote_average = in.readString();
        this.backdrop_path = in.readString();
        this.favorite = in.readByte() != 0;
    }

    public static final Parcelable.Creator<MovieEntity> CREATOR = new Parcelable.Creator<MovieEntity>() {
        @Override
        public MovieEntity createFromParcel(Parcel source) {
            return new MovieEntity(source);
        }

        @Override
        public MovieEntity[] newArray(int size) {
            return new MovieEntity[size];
        }
    };
}
