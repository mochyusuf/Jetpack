package mocha.yusuf.jetpack3.Data.Source.Remote;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import mocha.yusuf.jetpack3.API.APIService;
import mocha.yusuf.jetpack3.BuildConfig;
import mocha.yusuf.jetpack3.Data.Source.Remote.Response.MovieResponse;
import mocha.yusuf.jetpack3.Data.Source.Remote.Response.TVResponse;
import mocha.yusuf.jetpack3.Model.MovieModel;
import mocha.yusuf.jetpack3.Model.TVModel;
import mocha.yusuf.jetpack3.Utils.EspressoIdlingResource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepository {
    private static RemoteRepository INSTANCE;
    private APIService apiService;
    private static final String LANGUAGE = "en-US";
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private RemoteRepository(APIService apiService) {
        this.apiService = apiService;
    }

    public static RemoteRepository getInstance(APIService apiService) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(apiService);
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<List<MovieModel>>> getMovies() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<MovieModel>>> resultMovies = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Call<MovieResponse> call = apiService.get_movies(BuildConfig.API_KEY, LANGUAGE);
            call.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                    if (response.body() != null) {
                        resultMovies.setValue(ApiResponse.success(response.body().getMovies()));
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }
                }

                @Override
                public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                    EspressoIdlingResource.decrement();
                }
            });

        }, SERVICE_LATENCY_IN_MILLIS);

        return resultMovies;
    }

    public LiveData<ApiResponse<List<TVModel>>> getTvs() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<TVModel>>> resultTvShow = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Call<TVResponse> call = apiService.get_tvs(BuildConfig.API_KEY, LANGUAGE);
            call.enqueue(new Callback<TVResponse>() {
                @Override
                public void onResponse(@NonNull Call<TVResponse> call, @NonNull Response<TVResponse> response) {
                    if (response.body() != null) {
                        resultTvShow.setValue(ApiResponse.success(response.body().getTvModels()));
                        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement();
                        }
                    }
                }

                @Override
                public void onFailure(@NotNull Call<TVResponse> call, @NotNull Throwable t) {
                    EspressoIdlingResource.decrement();
                }
            });

        }, SERVICE_LATENCY_IN_MILLIS);

        return resultTvShow;
    }
}
