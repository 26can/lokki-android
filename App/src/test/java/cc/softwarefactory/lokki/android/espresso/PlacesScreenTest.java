package cc.softwarefactory.lokki.android.espresso;

import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.okhttp.mockwebserver.MockResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import cc.softwarefactory.lokki.android.MainApplication;
import cc.softwarefactory.lokki.android.R;
import cc.softwarefactory.lokki.android.espresso.utilities.MockJsonUtils;
import cc.softwarefactory.lokki.android.espresso.utilities.TestUtils;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;


public class PlacesScreenTest extends MainActivityBaseTest {


    @Override
    public void setUp() throws Exception {
        super.setUp();
        //enterPlacesScreen();
    }

    private void enterPlacesScreen() {
        getActivity();
        onView(withText(R.string.places)).perform((click()));
    }


    public void testEmptyPlacesScreen() {
        enterPlacesScreen();
        onView(withText(R.string.places_how_to_create)).check(matches(isDisplayed()));
    }


    public void testPlacesOnPlacesScreen() throws JSONException {
        mockDispatcher.setPlacesResponse(new MockResponse().setBody(MockJsonUtils.getPlacesJson()));
        enterPlacesScreen();
        onView(withText("Testplace1")).check(matches(isDisplayed()));
    }


    public void testContactAppearsInPlace() throws JSONException {
        mockDispatcher.setPlacesResponse(new MockResponse().setBody(MockJsonUtils.getPlacesJson()));
        String[] contactEmails = (new String[]{"family.member@example.com"});
        JSONObject location = new JSONObject();
        location.put("lat", "37.483477313364574") //testPlace1
                .put("lon", "-122.14838393032551")
                .put("rad", "100");
        JSONObject[] locations = (new JSONObject[]{location});
        mockDispatcher.setDashboardResponse(new MockResponse().setBody(MockJsonUtils
                .getDashboardJsonWithContactsAndLocations(contactEmails, locations)));
        enterPlacesScreen();

        //onView(withId(R.id.avatar_row)).check(matches(isDisplayed()));
        //onView(withId(R.id.avatar_row)).check(matches(hasDescendant(isAssignableFrom(ImageView.class))));
    }
}
