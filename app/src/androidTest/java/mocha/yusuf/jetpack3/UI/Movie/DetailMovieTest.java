package mocha.yusuf.jetpack3.UI.Movie;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import mocha.yusuf.jetpack3.Data.Source.Local.Entity.MovieEntity;
import mocha.yusuf.jetpack3.R;
import mocha.yusuf.jetpack3.Utils.EspressoIdlingResource;
import mocha.yusuf.jetpack3.Utils.FakeDataDummy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class DetailMovieTest {
    private MovieEntity dummyMovie = FakeDataDummy.generateDummyRemoteMovie().get(0);

    @Rule
    public ActivityTestRule<DetailMovie> activityRule = new ActivityTestRule<DetailMovie>(DetailMovie.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailMovie.class);
            result.putExtra(DetailMovie.EXTRA_MOVIE, dummyMovie);
            return result;
        }
    };

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadMovies() {
        onView(withId(R.id.movie_image)).check(matches(isDisplayed()));
        onView(withId(R.id.movie_title)).check(matches(isDisplayed()));
        onView(withId(R.id.movie_release_date)).check(matches(isDisplayed()));
        onView(withId(R.id.movie_score)).check(matches(isDisplayed()));
        onView(withId(R.id.movie_overview)).check(matches(isDisplayed()));
    }
}
