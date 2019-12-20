package mocha.yusuf.jetpack3.UI.TV;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import mocha.yusuf.jetpack3.Data.Source.Local.Entity.TVEntity;
import mocha.yusuf.jetpack3.R;
import mocha.yusuf.jetpack3.Utils.EspressoIdlingResource;
import mocha.yusuf.jetpack3.Utils.FakeDataDummy;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class DetailTVTest {
    private TVEntity dummyTv = FakeDataDummy.generateDummyRemoteTv().get(0);

    @Rule
    public ActivityTestRule<DetailTV> activityRule = new ActivityTestRule<DetailTV>(DetailTV.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailTV.class);
            result.putExtra(DetailTV.EXTRA_TV, dummyTv);
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
    public void loadTV() {
        onView(withId(R.id.tv_image)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_first_air_date)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()));
    }
}
