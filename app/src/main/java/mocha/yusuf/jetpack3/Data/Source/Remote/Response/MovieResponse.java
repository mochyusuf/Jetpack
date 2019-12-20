package mocha.yusuf.jetpack3.Data.Source.Remote.Response;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import mocha.yusuf.jetpack3.Model.MovieModel;

public class MovieResponse {
    @SerializedName("results")
    private List<MovieModel> movies;

    public List<MovieModel> getMovies() {
        return movies;
    }
}
