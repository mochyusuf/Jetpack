package mocha.yusuf.jetpack3.Data.Source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import java.util.List;

import mocha.yusuf.jetpack3.Data.Source.Local.Entity.MovieEntity;
import mocha.yusuf.jetpack3.Data.Source.Local.Entity.TVEntity;
import mocha.yusuf.jetpack3.Utils.Resource;

public interface DataSource {
    LiveData<Resource<List<MovieEntity>>> get_movies();

    LiveData<Resource<List<TVEntity>>> get_tvs();

    LiveData<Resource<PagedList<MovieEntity>>> get_favorite_movie();

    LiveData<Resource<PagedList<TVEntity>>> get_favorite_tv();

    void set_favorite_movie(MovieEntity movie, boolean state);

    void set_favorite_tv(TVEntity tvShow, boolean state);
}
