package mocha.yusuf.jetpack3.UI.Movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import mocha.yusuf.jetpack3.Data.Source.Local.Entity.MovieEntity;
import mocha.yusuf.jetpack3.Data.Source.Repository;
import mocha.yusuf.jetpack3.Utils.FakeDataDummy;
import mocha.yusuf.jetpack3.Utils.Resource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieViewModelTest {
    private MovieViewModel movieViewModel;
    private Repository repository = mock(Repository.class);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        movieViewModel = new MovieViewModel(repository);
    }

    @Test
    public void getMovies() {
        Resource<List<MovieEntity>> resource = Resource.success(FakeDataDummy.generateDummyRemoteMovie());
        MutableLiveData<Resource<List<MovieEntity>>> movies = new MutableLiveData<>();
        movies.setValue(resource);

        when(repository.get_movies()).thenReturn(movies);
        Observer<Resource<List<MovieEntity>>> observer = mock(Observer.class);
        movieViewModel.setMovieAction("load");
        movieViewModel.movies.observeForever(observer);
        verify(observer).onChanged(resource);
    }
}
