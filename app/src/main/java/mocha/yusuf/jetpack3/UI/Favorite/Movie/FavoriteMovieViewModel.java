package mocha.yusuf.jetpack3.UI.Favorite.Movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import mocha.yusuf.jetpack3.Data.Source.Local.Entity.MovieEntity;
import mocha.yusuf.jetpack3.Data.Source.Repository;
import mocha.yusuf.jetpack3.Utils.Resource;

public class FavoriteMovieViewModel extends ViewModel {

    private Repository repository;

    public FavoriteMovieViewModel(Repository repository) {
        this.repository = repository;
    }

    LiveData<Resource<PagedList<MovieEntity>>> getFavoriteMovie() {
        return repository.get_favorite_movie();
    }

    void setFavoriteMovie(MovieEntity movieEntity) {
        final boolean state = !movieEntity.isFavorite();
        repository.set_favorite_movie(movieEntity, state);
    }
}

