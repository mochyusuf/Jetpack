package mocha.yusuf.jetpack3.UI.Movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

import mocha.yusuf.jetpack3.Data.Source.Local.Entity.MovieEntity;
import mocha.yusuf.jetpack3.Data.Source.Repository;
import mocha.yusuf.jetpack3.Utils.Resource;

public class MovieViewModel extends ViewModel {
    private Repository repository;
    private MutableLiveData<String> movie = new MutableLiveData<>();

    public MovieViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<List<MovieEntity>>> movies = Transformations.switchMap(movie,
            data -> repository.get_movies());

    public void setMovieAction(String action) {
        movie.setValue(action);
    }

    public void toggleMovieFavorite(MovieEntity movieEntity) {
        final boolean favorite = !movieEntity.isFavorite();
        repository.set_favorite_movie(movieEntity, favorite);
    }
}
