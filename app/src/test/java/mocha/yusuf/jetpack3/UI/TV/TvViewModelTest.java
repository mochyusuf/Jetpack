package mocha.yusuf.jetpack3.UI.TV;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import mocha.yusuf.jetpack3.Data.Source.Local.Entity.TVEntity;
import mocha.yusuf.jetpack3.Data.Source.Repository;
import mocha.yusuf.jetpack3.Utils.FakeDataDummy;
import mocha.yusuf.jetpack3.Utils.Resource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TvViewModelTest {
    private TvViewModel tvViewModel;
    private Repository repository = mock(Repository.class);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        tvViewModel = new TvViewModel(repository);
    }

    @Test
    public void getTvs() {
        Resource<List<TVEntity>> resource = Resource.success(FakeDataDummy.generateDummyRemoteTv());
        MutableLiveData<Resource<List<TVEntity>>> tvs = new MutableLiveData<>();
        tvs.setValue(resource);

        when(repository.get_tvs()).thenReturn(tvs);
        Observer<Resource<List<TVEntity>>> observer = mock(Observer.class);
        tvViewModel.setTvAction("load");
        tvViewModel.tvShows.observeForever(observer);
        verify(observer).onChanged(resource);
    }
}
