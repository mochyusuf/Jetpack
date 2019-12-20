package mocha.yusuf.jetpack3.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import mocha.yusuf.jetpack3.Data.Source.Repository;
import mocha.yusuf.jetpack3.UI.Favorite.Movie.FavoriteMovieViewModel;
import mocha.yusuf.jetpack3.UI.Favorite.TV.FavoriteTvViewModel;
import mocha.yusuf.jetpack3.UI.Movie.MovieViewModel;
import mocha.yusuf.jetpack3.UI.TV.TvViewModel;
import mocha.yusuf.jetpack3.Utils.Injection;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final Repository repository;

    private ViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.repository(application));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            return (T) new MovieViewModel(repository);
        } else if (modelClass.isAssignableFrom(TvViewModel.class)) {
            return (T) new TvViewModel(repository);
        } else if (modelClass.isAssignableFrom(FavoriteMovieViewModel.class)) {
            return (T) new FavoriteMovieViewModel(repository);
        } else if (modelClass.isAssignableFrom(FavoriteTvViewModel.class)) {
            return (T) new FavoriteTvViewModel(repository);
        }

        throw new IllegalArgumentException("Unknown Viewmodel class : " + modelClass.getName());
    }
}
