package mocha.yusuf.jetpack3.Data.Source.Local.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "TVs")
public class TVEntity implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private long id;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private String poster_path;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;

    @ColumnInfo(name = "first_air_date")
    @SerializedName("first_air_date")
    private String first_air_date;

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

    public TVEntity(long id, String poster_path, String name, String first_air_date, String overview, String original_language, String vote_average, String backdrop_path, Boolean favorite) {
        this.id = id;
        this.poster_path = poster_path;
        this.name = name;
        this.first_air_date = first_air_date;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_air_date() {
        return first_air_date;
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
        dest.writeString(this.name);
        dest.writeString(this.first_air_date);
        dest.writeString(this.overview);
        dest.writeString(this.original_language);
        dest.writeString(this.vote_average);
        dest.writeString(this.backdrop_path);
        dest.writeByte(this.favorite ? (byte) 1 : (byte) 0);
    }

    protected TVEntity(Parcel in) {
        this.id = in.readLong();
        this.poster_path = in.readString();
        this.name = in.readString();
        this.first_air_date = in.readString();
        this.overview = in.readString();
        this.original_language = in.readString();
        this.vote_average = in.readString();
        this.backdrop_path = in.readString();
        this.favorite = in.readByte() != 0;
    }

    public static final Parcelable.Creator<TVEntity> CREATOR = new Parcelable.Creator<TVEntity>() {
        @Override
        public TVEntity createFromParcel(Parcel source) {
            return new TVEntity(source);
        }

        @Override
        public TVEntity[] newArray(int size) {
            return new TVEntity[size];
        }
    };
}
