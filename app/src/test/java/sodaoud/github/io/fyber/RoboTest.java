package sodaoud.github.io.fyber;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import sodaoud.github.io.fyber.data.FyberResponse;
import sodaoud.github.io.fyber.util.Util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by sofiane on 9/18/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class RoboTest {

    private OffersActivity offersActivity;
    private MainActivity mainActivity;

    @Before
    public void setup() {
        Intent i = new Intent();
        i.putExtra(OffersActivity.RESPONSE, new Gson().fromJson(UtilTest.json, FyberResponse.class));

        offersActivity = Robolectric.buildActivity(OffersActivity.class, i).setup().get();
        mainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void mainActivityTest() throws Exception {

        Button fillBtn = (Button) mainActivity.findViewById(R.id.fill_form_btn);
        EditText uid = (EditText) mainActivity.findViewById(R.id.uid);
        assertNotNull(fillBtn);
        assertNotNull(uid);
        assertEquals(true, TextUtils.isEmpty(uid.getText()));

        fillBtn.performClick();
        assertEquals(false, TextUtils.isEmpty(uid.getText()));

        assertEquals(mainActivity.params.containsKey(Util.HASH_KEY), false);
        mainActivity.addHashKey();
        assertEquals(mainActivity.params.containsKey(Util.HASH_KEY), true);
        assertEquals(mainActivity.params.get(Util.HASH_KEY), "ae2a533f09bca44c97e7a6f64da3fefec8b83b3e");

    }

    @Test
    public void offersActivityTest() throws Exception {
        RecyclerView list = (RecyclerView) offersActivity.findViewById(R.id.list);
        assertNotNull(list);
        assertEquals(list.getAdapter().getItemCount(), 30);
        View v = list.getLayoutManager().findViewByPosition(0);

        TextView titleView = (TextView) v.findViewById(R.id.title);

        assertEquals(titleView.getText().toString(), "Colors Mania [Web Game]");
    }
}
