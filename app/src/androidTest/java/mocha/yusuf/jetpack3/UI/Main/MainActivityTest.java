package mocha.yusuf.jetpack3.UI.Main;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.tabs.TabLayout;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import mocha.yusuf.jetpack3.R;
import mocha.yusuf.jetpack3.Utils.EspressoIdlingResource;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

public class MainActivityTest {
    private Integer sleepTime = 3000;
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void toDetailMovieActivityTest() {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.recyler_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.recyler_movie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.movie_title)).check(matches(isDisplayed()));
        onView(withId(R.id.movie_release_date)).check(matches(isDisplayed()));
        onView(withId(R.id.movie_score)).check(matches(isDisplayed()));
        onView(withId(R.id.movie_overview)).check(matches(isDisplayed()));
        onView(withId(R.id.movie_image)).check(matches(isDisplayed()));
    }

    @Test
    public void toDetailTvActivityTest() {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.nav_tv)).check(matches(isDisplayed()));
        onView(withId(R.id.nav_tv)).perform(click());

        onView(withId(R.id.recyler_tv)).check(matches(isDisplayed()));
        onView(withId(R.id.recyler_tv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_first_air_date)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_image)).check(matches(isDisplayed()));
    }

    @Test
    public void toFavoriteMovie() {
        onView(withId(R.id.nav_favorite)).check(matches(isDisplayed()));
        onView(withId(R.id.nav_favorite)).perform(click());

        onView(withId(R.id.recyler_favorite_movie)).check(matches(isDisplayed()));
    }

    @Test
    public void toFavoriteTV() {
        onView(withId(R.id.nav_favorite)).check(matches(isDisplayed()));
        onView(withId(R.id.nav_favorite)).perform(click());
        onView(withId(R.id.tablayout)).perform(selectTabAtPosition(1));

        onView(withId(R.id.recyler_favorite_tv)).check(matches(isDisplayed()));
    }

    @NonNull
    private static ViewAction selectTabAtPosition(final int position) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(TabLayout.class));
            }

            @Override
            public String getDescription() {
                return "with tab at index" + String.valueOf(position);
            }

            @Override
            public void perform(UiController uiController, View view) {
                if (view instanceof TabLayout) {
                    TabLayout tabLayout = (TabLayout) view;
                    TabLayout.Tab tab = tabLayout.getTabAt(position);

                    if (tab != null) {
                        tab.select();
                    }
                }
            }
        };
    }
}
