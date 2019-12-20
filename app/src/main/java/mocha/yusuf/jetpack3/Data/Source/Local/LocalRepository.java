package mocha.yusuf.jetpack3.Data.Source.Local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import java.util.List;

import mocha.yusuf.jetpack3.Data.Source.Local.Entity.MovieEntity;
import mocha.yusuf.jetpack3.Data.Source.Local.Entity.TVEntity;
import mocha.yusuf.jetpack3.Data.Source.Local.Room.DatabaseDao;

public class LocalRepository {
    private final DatabaseDao databaseDao;

    private LocalRepository(DatabaseDao databaseDao) {
        this.databaseDao = databaseDao;
    }

    private static LocalRepository INSTANCE;

    public static LocalRepository get_instance(DatabaseDao databaseDao) {
        if (INSTANCE == null)
            INSTANCE = new LocalRepository(databaseDao);
        return INSTANCE;
    }

    public LiveData<List<MovieEntity>> get_movies() {
        return databaseDao.get_movies();
    }

    public void insert_movies(List<MovieEntity> movieEntities) {
        databaseDao.insert_movie(movieEntities);
    }

    public DataSource.Factory<Integer, MovieEntity> get_favorite_movie() {
        return databaseDao.get_favorite_movie();
    }

    public void set_favorite_movie(MovieEntity movieEntity, boolean state) {
        movieEntity.setFavorite(state);
        databaseDao.update_movie(movieEntity);
    }

    public LiveData<List<TVEntity>> get_tvs() {
        return databaseDao.get_tvs();
    }

    public void insert_tv(List<TVEntity> tvEntities) {
        databaseDao.insert_tv(tvEntities);
    }

    public DataSource.Factory<Integer, TVEntity> get_favorite_tv() {
        return databaseDao.get_favorite_tv();
    }

    public void set_favorite_tv(TVEntity tvEntity, boolean state) {
        tvEntity.setFavorite(state);
        databaseDao.update_tv(tvEntity);
    }
}
