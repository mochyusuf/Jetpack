package mocha.yusuf.jetpack3.UI.Movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import mocha.yusuf.jetpack3.BuildConfig;
import mocha.yusuf.jetpack3.Data.Source.Local.Entity.MovieEntity;
import mocha.yusuf.jetpack3.R;

public class DetailMovie extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    private static MovieEntity movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        TextView title = findViewById(R.id.movie_title);
        ImageView image = findViewById(R.id.movie_image);
        TextView year = findViewById(R.id.movie_release_date);
        TextView score = findViewById(R.id.movie_score);
        TextView description = findViewById(R.id.movie_overview);

        title.setText(movie.getTitle());
        Glide.with(this)
                .load(BuildConfig.API_IMAGE +movie.getPoster_path())
                .into(image);
        year.setText(String.valueOf(movie.getRelease_date()));
        score.setText(movie.getVote_average());
        description.setText(movie.getOverview());
    }
}
