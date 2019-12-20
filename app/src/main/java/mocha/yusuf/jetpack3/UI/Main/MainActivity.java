package mocha.yusuf.jetpack3.UI.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import mocha.yusuf.jetpack3.R;
import mocha.yusuf.jetpack3.UI.Favorite.FavoriteFragment;
import mocha.yusuf.jetpack3.UI.Movie.MovieFragment;
import mocha.yusuf.jetpack3.UI.TV.TVFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState==null){
            navigation.setSelectedItemId(R.id.nav_movie);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        Fragment fragment;

        switch (item.getItemId()) {
            case R.id.nav_movie:

                fragment = new MovieFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                        .commit();
                return true;
            case R.id.nav_tv:

                fragment = new TVFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                        .commit();
                return true;
            case R.id.nav_favorite:

                fragment = new FavoriteFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                        .commit();
                return true;
        }
        return false;
    };
}
