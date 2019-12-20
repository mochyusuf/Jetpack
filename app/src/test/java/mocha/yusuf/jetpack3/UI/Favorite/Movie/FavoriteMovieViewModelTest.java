package mocha.yusuf.jetpack3.UI.Favorite.Movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import mocha.yusuf.jetpack3.Data.Source.Local.Entity.MovieEntity;
import mocha.yusuf.jetpack3.Data.Source.Repository;
import mocha.yusuf.jetpack3.Utils.Resource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavoriteMovieViewModelTest {
    private Repository repository = mock(Repository.class);
    private FavoriteMovieViewModel favoriteMovieViewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        favoriteMovieViewModel = new FavoriteMovieViewModel(repository);
    }

    @Test
    public void getFavoriteMovie() {
        MutableLiveData<Resource<PagedList<MovieEntity>>> movies = new MutableLiveData<>();
        PagedList<MovieEntity> pagedList = mock(PagedList.class);
        movies.setValue(Resource.success(pagedList));

        when(repository.get_favorite_movie()).thenReturn(movies);
        Observer<Resource<PagedList<MovieEntity>>> observer = mock(Observer.class);
        favoriteMovieViewModel.getFavoriteMovie().observeForever(observer);
        verify(observer).onChanged(Resource.success(pagedList));
    }
}
