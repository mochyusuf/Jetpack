package mocha.yusuf.jetpack3.Data.Source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import mocha.yusuf.jetpack3.Data.Source.Local.Entity.MovieEntity;
import mocha.yusuf.jetpack3.Data.Source.Local.Entity.TVEntity;
import mocha.yusuf.jetpack3.Data.Source.Local.LocalRepository;
import mocha.yusuf.jetpack3.Data.Source.Remote.RemoteRepository;
import mocha.yusuf.jetpack3.Model.TVModel;
import mocha.yusuf.jetpack3.Utils.FakeDataDummy;
import mocha.yusuf.jetpack3.Utils.InstantAppExecutors;
import mocha.yusuf.jetpack3.Utils.LiveDataTestUtil;
import mocha.yusuf.jetpack3.Utils.PagedListUtil;
import mocha.yusuf.jetpack3.Utils.Resource;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RepositoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private LocalRepository localRepository = mock(LocalRepository.class);
    private RemoteRepository remoteRepository = mock(RemoteRepository.class);
    private InstantAppExecutors instantAppExecutors = mock(InstantAppExecutors.class);
    private FakeRepository fakeRepository = new FakeRepository(localRepository, remoteRepository, instantAppExecutors);

    private List<MovieEntity> movies = FakeDataDummy.generateDummyRemoteMovie();
    private List<TVEntity> tvs = FakeDataDummy.generateDummyRemoteTv();

    @Test
    public void getMovies() {
        MutableLiveData<List<MovieEntity>> dummyMovies = new MutableLiveData<>();
        dummyMovies.setValue(FakeDataDummy.generateDummyRemoteMovie());

        when(localRepository.get_movies()).thenReturn(dummyMovies);
        Resource<List<MovieEntity>> result = LiveDataTestUtil.getValue(fakeRepository.get_movies());

        verify(localRepository).get_movies();
        assertNotNull(result.data);
        assertEquals(movies.size(), result.data.size());
    }

    @Test
    public void getTvs() {
        MutableLiveData<List<TVEntity>> dummyTvShow = new MutableLiveData<>();
        dummyTvShow.setValue(FakeDataDummy.generateDummyRemoteTv());

        when(localRepository.get_tvs()).thenReturn(dummyTvShow);
        Resource<List<TVEntity>> result = LiveDataTestUtil.getValue(fakeRepository.get_tvs());

        verify(localRepository).get_tvs();
        assertNotNull(result.data);
        assertEquals(tvs.size(), result.data.size());
    }

    @Test
    public void getFavoriteMovies() {
        DataSource.Factory<Integer, MovieEntity> dataSourceFactory = mock(DataSource.Factory.class);

        when(localRepository.get_favorite_movie()).thenReturn(dataSourceFactory);
        fakeRepository.get_favorite_movie();
        Resource<PagedList<MovieEntity>> resultFavorite = Resource.success(PagedListUtil.mockPagedList(movies));

        verify(localRepository).get_favorite_movie();
        assertNotNull(resultFavorite.data);
        assertEquals(movies.size(), resultFavorite.data.size());

    }

    @Test
    public void getFavoriteTvs() {
        DataSource.Factory<Integer, TVEntity> dataSourceFactory = mock(DataSource.Factory.class);

        when(localRepository.get_favorite_tv()).thenReturn(dataSourceFactory);
        fakeRepository.get_favorite_tv();
        Resource<PagedList<TVEntity>> resultFavorite = Resource.success(PagedListUtil.mockPagedList(tvs));

        verify(localRepository).get_favorite_tv();
        assertNotNull(resultFavorite.data);
        assertEquals(tvs.size(), resultFavorite.data.size());
    }
}
