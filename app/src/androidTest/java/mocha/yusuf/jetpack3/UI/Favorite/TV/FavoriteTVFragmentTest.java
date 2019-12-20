package mocha.yusuf.jetpack3.UI.Favorite.TV;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import mocha.yusuf.jetpack3.R;
import mocha.yusuf.jetpack3.Utils.EspressoIdlingResource;
import mocha.yusuf.jetpack3.testing.SingleFragmentActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class FavoriteTVFragmentTest {
    @Rule
    public final ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private final FavoriteTVFragment favoriteTVFragment = new FavoriteTVFragment();

    @Before
    public void setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        activityRule.getActivity().setFragment(favoriteTVFragment);
    }

    @After
    public void teardown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadMovies() {
        onView(withId(R.id.recyler_favorite_tv)).check(matches(isDisplayed()));
    }
}
