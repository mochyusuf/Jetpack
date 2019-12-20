package mocha.yusuf.jetpack3.UI.Favorite.TV;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import mocha.yusuf.jetpack3.Data.Source.Local.Entity.TVEntity;
import mocha.yusuf.jetpack3.Data.Source.Repository;
import mocha.yusuf.jetpack3.Utils.Resource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavoriteTvViewModelTest {
    private Repository repository = mock(Repository.class);
    private FavoriteTvViewModel favoriteTvViewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        favoriteTvViewModel = new FavoriteTvViewModel(repository);
    }

    @Test
    public void getFavoriteTV() {
        MutableLiveData<Resource<PagedList<TVEntity>>> tvShows = new MutableLiveData<>();
        PagedList<TVEntity> pagedList = mock(PagedList.class);
        tvShows.setValue(Resource.success(pagedList));

        when(repository.get_favorite_tv()).thenReturn(tvShows);
        Observer<Resource<PagedList<TVEntity>>> observer = mock(Observer.class);
        favoriteTvViewModel.getFavoriteTv().observeForever(observer);
        verify(observer).onChanged(Resource.success(pagedList));
    }
}
