package mocha.yusuf.jetpack3.UI.TV;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import mocha.yusuf.jetpack3.R;
import mocha.yusuf.jetpack3.Utils.EspressoIdlingResource;
import mocha.yusuf.jetpack3.Utils.RecyclerViewItemCountAssertion;
import mocha.yusuf.jetpack3.testing.SingleFragmentActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class TVFragmentTest {
    @Rule
    public final ActivityTestRule<SingleFragmentActivity> activityTestRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private final TVFragment tvShowFragment = new TVFragment();

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        activityTestRule.getActivity().setFragment(tvShowFragment);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadTv() {
        onView(withId(R.id.recyler_tv)).check(matches(isDisplayed()));
        onView(withId(R.id.recyler_tv)).check(new RecyclerViewItemCountAssertion(20));
    }
}
