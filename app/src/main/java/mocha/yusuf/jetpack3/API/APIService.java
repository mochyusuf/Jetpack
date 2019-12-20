package mocha.yusuf.jetpack3.API;

import mocha.yusuf.jetpack3.Data.Source.Remote.Response.MovieResponse;
import mocha.yusuf.jetpack3.Data.Source.Remote.Response.TVResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("discover/movie")
    Call<MovieResponse> get_movies(
            @Query("api_key") String api_key,
            @Query("language") String language
    );

    @GET("discover/tv")
    Call<TVResponse> get_tvs(
            @Query("api_key") String api_key,
            @Query("language") String language
    );
}
