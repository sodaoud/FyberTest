package sodaoud.github.io.fybertest;

import android.support.test.espresso.intent.Intents;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;

import sodaoud.github.io.fyber.MainActivity;
import sodaoud.github.io.fyber.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static sodaoud.github.io.fyber.R.id.submit_btn;

/**
 * Created by sofiane on 9/18/16.
 */

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intents.init();
    }

    @SmallTest
    public void testButton(){
        Button submit = (Button) getActivity().findViewById(submit_btn);
        assertNotNull(submit);
        onView(withId(R.id.fill_form_btn)).perform(click());
        onView(withId(R.id.uid)).check(matches(withText("spiderman")));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        Intents.release();
    }
}
