package mocha.yusuf.jetpack3.UI.Favorite.TV;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import mocha.yusuf.jetpack3.Data.Source.Local.Entity.TVEntity;
import mocha.yusuf.jetpack3.Data.Source.Repository;
import mocha.yusuf.jetpack3.Utils.Resource;

public class FavoriteTvViewModel extends ViewModel {

    private Repository repository;

    public FavoriteTvViewModel(Repository repository) {
        this.repository = repository;
    }

    LiveData<Resource<PagedList<TVEntity>>> getFavoriteTv() {
        return repository.get_favorite_tv();
    }

    void setFavoriteTv(TVEntity tvEntity) {
        final boolean state = !tvEntity.isFavorite();
        repository.set_favorite_tv(tvEntity, state);
    }
}
