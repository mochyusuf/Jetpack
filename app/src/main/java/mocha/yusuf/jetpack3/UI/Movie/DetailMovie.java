package mocha.yusuf.jetpack3.UI.Movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import mocha.yusuf.jetpack3.BuildConfig;
import mocha.yusuf.jetpack3.Data.Source.Local.Entity.MovieEntity;
import mocha.yusuf.jetpack3.R;
import mocha.yusuf.jetpack3.ViewModel.ViewModelFactory;

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
        ImageView favorite = findViewById(R.id.movie_favorite);

        title.setText(movie.getTitle());
        Glide.with(this)
                .load(BuildConfig.API_IMAGE +movie.getPoster_path())
                .into(image);
        year.setText(String.valueOf(movie.getRelease_date()));
        score.setText(movie.getVote_average());
        description.setText(movie.getOverview());

        if (movie.isFavorite()){
            favorite.setImageResource(R.drawable.ic_favorite_black_24dp);
        }
        MovieViewModel movieViewModel = obtainViewModel();
        favorite.setOnClickListener(v -> {
            if (movie.isFavorite()) {
                favorite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                Toast.makeText(this, R.string.movie_unfavorite, Toast.LENGTH_SHORT).show();
                movieViewModel.toggleMovieFavorite(movie);
            } else {
                favorite.setImageResource(R.drawable.ic_favorite_black_24dp);
                Toast.makeText(this, R.string.movie_favorite, Toast.LENGTH_SHORT).show();
                movieViewModel.toggleMovieFavorite(movie);
            }
        });
    }

    private MovieViewModel obtainViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(MovieViewModel.class);
    }
}
