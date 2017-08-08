package cuongvo.mvp_example.view;

import android.support.test.espresso.matcher.ViewMatchers;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import cuongvo.mvp_example.MainActivity;
import cuongvo.mvp_example.base.BaseTest;
import cuongvo.mvp_example.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by cuongvo on 7/23/17.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends BaseTest {

    private MainActivity mMainActivity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws IOException {
        mMainActivity = mActivityRule.getActivity();
        assertThat(mMainActivity, notNullValue());

    }

    @Test
    public void checkTabLayoutDisplayed() {
        onView(withId(R.id.main_activity_tabs)).perform(click()).check(matches(isDisplayed()));
    }

    /**
     * Swipe to left
     */
    @Test
    public void swipePage() {
        onView(withId(R.id.main_activity_pager)).check(matches(isDisplayed()));
        onView(withId(R.id.main_activity_pager)).perform(swipeLeft());
    }

    /**
     * Swipe to the end of a Fragment
     */
    @Test
    public void testSwipeToTheEnd() {
        onView(withId(R.id.main_activity_pager)).check(matches(isDisplayed()));
        onView(withId(R.id.main_activity_pager)).perform(swipeLeft()).perform(swipeLeft()).perform(swipeLeft());
        onView(allOf(withId(R.id.fragment_eat_container), isCompletelyDisplayed())).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
}
