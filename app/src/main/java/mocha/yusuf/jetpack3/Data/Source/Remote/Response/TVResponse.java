package mocha.yusuf.jetpack3.Data.Source.Remote.Response;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import mocha.yusuf.jetpack3.Model.TVModel;

public class TVResponse {
    @SerializedName("results")
    private List<TVModel> tvModels;

    public List<TVModel> getTvModels() {
        return tvModels;
    }
}
