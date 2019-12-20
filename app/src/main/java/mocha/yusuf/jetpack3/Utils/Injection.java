package mocha.yusuf.jetpack3.Utils;

import android.app.Application;

import mocha.yusuf.jetpack3.API.APIService;
import mocha.yusuf.jetpack3.API.APIUrl;
import mocha.yusuf.jetpack3.Data.Source.Local.LocalRepository;
import mocha.yusuf.jetpack3.Data.Source.Local.Room.Database;
import mocha.yusuf.jetpack3.Data.Source.Remote.RemoteRepository;
import mocha.yusuf.jetpack3.Data.Source.Repository;

public class Injection {
    public static Repository repository(Application application) {
        Database database = Database.getINSTANCE(application);

        LocalRepository localRepository = LocalRepository.get_instance(database.databaseDao());
        RemoteRepository remoteRepository = RemoteRepository.getInstance(APIUrl.getClient().create(APIService.class));
        AppExecutors appExecutors = new AppExecutors();
        return Repository.getInstance(localRepository, remoteRepository, appExecutors);
    }
}