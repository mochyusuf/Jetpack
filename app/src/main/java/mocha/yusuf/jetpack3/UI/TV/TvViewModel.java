package mocha.yusuf.jetpack3.UI.TV;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

import mocha.yusuf.jetpack3.Data.Source.Local.Entity.TVEntity;
import mocha.yusuf.jetpack3.Data.Source.Repository;
import mocha.yusuf.jetpack3.Utils.Resource;

public class TvViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<String> tv = new MutableLiveData<>();


    public TvViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<List<TVEntity>>> tvShows = Transformations.switchMap(tv,
            data -> repository.get_tvs());

    void setTvAction(String action) {
        tv.setValue(action);
    }

    public void toggleTvFavorite(TVEntity tvEntity) {
        final boolean favorite = !tvEntity.isFavorite();
        repository.set_favorite_tv(tvEntity, favorite);
    }
}