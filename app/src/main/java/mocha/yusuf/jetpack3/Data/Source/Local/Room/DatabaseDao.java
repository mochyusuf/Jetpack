package mocha.yusuf.jetpack3.Data.Source.Local.Room;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import mocha.yusuf.jetpack3.Data.Source.Local.Entity.MovieEntity;
import mocha.yusuf.jetpack3.Data.Source.Local.Entity.TVEntity;

@Dao
public interface DatabaseDao {
    @WorkerThread
    @Query("SELECT * FROM Movies")
    LiveData<List<MovieEntity>> get_movies();

    @Query("SELECT * FROM Movies WHERE id = :id")
    LiveData<MovieEntity> get_detail_movie(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert_movie(List<MovieEntity> movieEntities);

    @WorkerThread
    @Query("SELECT * FROM Movies WHERE favorite = 1")
    DataSource.Factory<Integer, MovieEntity> get_favorite_movie();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update_movie(MovieEntity movieEntity);

    @WorkerThread
    @Query("SELECT * FROM TVs")
    LiveData<List<TVEntity>> get_tvs();

    @Query("SELECT * FROM TVs WHERE id = :id")
    LiveData<TVEntity> get_detail_tv(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert_tv(List<TVEntity> tvEntities);

    @WorkerThread
    @Query("SELECT * FROM TVs WHERE favorite = 1")
    DataSource.Factory<Integer, TVEntity> get_favorite_tv();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update_tv(TVEntity tvEntity);
}
