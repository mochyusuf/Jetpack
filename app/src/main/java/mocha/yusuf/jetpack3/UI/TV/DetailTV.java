package mocha.yusuf.jetpack3.UI.TV;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import mocha.yusuf.jetpack3.BuildConfig;
import mocha.yusuf.jetpack3.Data.Source.Local.Entity.TVEntity;
import mocha.yusuf.jetpack3.R;
import mocha.yusuf.jetpack3.ViewModel.ViewModelFactory;

public class DetailTV extends AppCompatActivity {
    public static final String EXTRA_TV = "extra_tv";

    private static TVEntity tvEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);

        tvEntity = getIntent().getParcelableExtra(EXTRA_TV);

        TextView tv_title = findViewById(R.id.tv_title);
        ImageView tv_image = findViewById(R.id.tv_image);
        TextView tv_first_air_date = findViewById(R.id.tv_first_air_date);
        TextView tv_score = findViewById(R.id.tv_score);
        TextView tv_overview = findViewById(R.id.tv_overview);
        ImageView favorite = findViewById(R.id.tv_favorite);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv_title.setText(tvEntity.getName());
        Glide.with(this)
                .load(BuildConfig.API_IMAGE +tvEntity.getPoster_path())
                .into(tv_image);
        tv_first_air_date.setText(tvEntity.getFirst_air_date());
        tv_score.setText(tvEntity.getVote_average());
        tv_overview.setText(tvEntity.getOverview());

        if (tvEntity.isFavorite()){
            favorite.setImageResource(R.drawable.ic_favorite_black_24dp);
        }
        TvViewModel tvViewModel = obtainViewModel();
        favorite.setOnClickListener(v -> {
            if (tvEntity.isFavorite()) {
                favorite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                Toast.makeText(this, R.string.tv_unfavorite, Toast.LENGTH_SHORT).show();
                tvViewModel.toggleTvFavorite(tvEntity);
            } else {
                favorite.setImageResource(R.drawable.ic_favorite_black_24dp);
                Toast.makeText(this, R.string.tv_favorite, Toast.LENGTH_SHORT).show();
                tvViewModel.toggleTvFavorite(tvEntity);
            }
        });
    }

    private TvViewModel obtainViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(TvViewModel.class);
    }
}
