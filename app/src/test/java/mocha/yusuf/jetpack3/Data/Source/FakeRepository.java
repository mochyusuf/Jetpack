package mocha.yusuf.jetpack3.Data.Source;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import java.util.ArrayList;
import java.util.List;

import mocha.yusuf.jetpack3.Data.Source.Local.Entity.MovieEntity;
import mocha.yusuf.jetpack3.Data.Source.Local.Entity.TVEntity;
import mocha.yusuf.jetpack3.Data.Source.Local.LocalRepository;
import mocha.yusuf.jetpack3.Data.Source.Remote.ApiResponse;
import mocha.yusuf.jetpack3.Data.Source.Remote.RemoteRepository;
import mocha.yusuf.jetpack3.Model.MovieModel;
import mocha.yusuf.jetpack3.Model.TVModel;
import mocha.yusuf.jetpack3.Utils.AppExecutors;
import mocha.yusuf.jetpack3.Utils.Resource;

public class FakeRepository implements DataSource {

    private RemoteRepository remoteRepository;
    private final LocalRepository localRepository;
    private final AppExecutors appExecutors;

    private volatile static FakeRepository INSTANCE = null;

    FakeRepository(LocalRepository localRepository, RemoteRepository remoteRepository, AppExecutors appExecutors) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        this.appExecutors = appExecutors;
    }

    public static FakeRepository getInstance(LocalRepository localRepository, RemoteRepository remoteRepository, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (FakeRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FakeRepository(localRepository, remoteRepository, appExecutors);
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public LiveData<Resource<List<MovieEntity>>> get_movies() {
        return new NetworkBoundResource<List<MovieEntity>, List<MovieModel>>(appExecutors) {

            @Override
            protected LiveData<List<MovieEntity>> loadFromDB() {
                return localRepository.get_movies();
            }

            @Override
            protected Boolean shouldFetch(List<MovieEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<MovieModel>>> createCall() {
                return remoteRepository.getMovies();
            }

            @Override
            protected void saveCallResult(List<MovieModel> data) {
                List<MovieEntity> movieEntities = new ArrayList<>();

                for (MovieModel movie : data) {
                    movieEntities.add(new MovieEntity(
                            movie.getId(),
                            movie.getPoster_path(),
                            movie.getTitle(),
                            movie.getRelease_date(),
                            movie.getOverview(),
                            movie.getOriginal_language(),
                            movie.getVote_average(),
                            movie.getBackdrop_path(),
                            null));
                }
                localRepository.insert_movies(movieEntities);

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<TVEntity>>> get_tvs() {
        return new NetworkBoundResource<List<TVEntity>, List<TVModel>>(appExecutors) {

            @Override
            protected LiveData<List<TVEntity>> loadFromDB() {
                return localRepository.get_tvs();
            }

            @Override
            protected Boolean shouldFetch(List<TVEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<TVModel>>> createCall() {
                return remoteRepository.getTvs();
            }

            @Override
            protected void saveCallResult(List<TVModel> data) {
                List<TVEntity> tvEntities = new ArrayList<>();

                for (TVModel tvModel : data) {
                    tvEntities.add(new TVEntity(
                            tvModel.getId(),
                            tvModel.getPoster_path(),
                            tvModel.getName(),
                            tvModel.getFirst_air_date(),
                            tvModel.getOverview(),
                            tvModel.getOriginal_language(),
                            tvModel.getVote_average(),
                            tvModel.getBackdrop_path(),
                            null));
                }
                localRepository.insert_tv(tvEntities);

            }
        }.asLiveData();
    }

    @Override
    public void set_favorite_movie(MovieEntity movieEntity, boolean state) {
        Runnable runnable = () -> localRepository.set_favorite_movie(movieEntity, state);
        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void set_favorite_tv(TVEntity tvEntity, boolean state) {
        Runnable runnable = () -> localRepository.set_favorite_tv(tvEntity, state);
        appExecutors.diskIO().execute(runnable);

    }

    @Override
    public LiveData<Resource<PagedList<MovieEntity>>> get_favorite_movie() {
        return new NetworkBoundResource<PagedList<MovieEntity>, List<MovieModel>>(appExecutors) {

            @Override
            protected LiveData<PagedList<MovieEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.get_favorite_movie(), 20).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MovieEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<MovieModel>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<MovieModel> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<TVEntity>>> get_favorite_tv() {
        return new NetworkBoundResource<PagedList<TVEntity>, List<TVModel>>(appExecutors) {

            @Override
            protected LiveData<PagedList<TVEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.get_favorite_tv(), 20).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<TVEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<TVModel>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<TVModel> data) {

            }
        }.asLiveData();
    }
}

