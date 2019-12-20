package mocha.yusuf.jetpack3.UI.TV;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import mocha.yusuf.jetpack3.BuildConfig;
import mocha.yusuf.jetpack3.Data.Source.Local.Entity.TVEntity;
import mocha.yusuf.jetpack3.R;

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv_title.setText(tvEntity.getName());
        Glide.with(this)
                .load(BuildConfig.API_IMAGE +tvEntity.getPoster_path())
                .into(tv_image);
        tv_first_air_date.setText(tvEntity.getFirst_air_date());
        tv_score.setText(tvEntity.getVote_average());
        tv_overview.setText(tvEntity.getOverview());
    }
}
