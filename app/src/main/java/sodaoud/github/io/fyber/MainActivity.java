package sodaoud.github.io.fyber;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import sodaoud.github.io.fyber.data.Error;
import sodaoud.github.io.fyber.data.FyberResponse;
import sodaoud.github.io.fyber.util.Util;

import static sodaoud.github.io.fyber.util.Util.hashString;

public class MainActivity extends AppCompatActivity {

    private EditText mUid;
    private EditText mApiKey;
    private EditText mAppId;
    private EditText mPub0;

    private OkHttpClient client;
    Map<String, String> params = new HashMap<>();
    private String apiKey;
    private String googleAdId;
    private boolean googleAdIdLimitedTrackingEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUid = (EditText) findViewById(R.id.uid);
        mApiKey = (EditText) findViewById(R.id.api_key);
        mAppId = (EditText) findViewById(R.id.app_id);
        mPub0 = (EditText) findViewById(R.id.pub0);

        client = new OkHttpClient();

        params.put(Util.FORMAT_KEY, Util.FORMAT_PARAM);
        params.put(Util.LOCALE_KEY, Locale.getDefault().getCountry());

        new Thread(new Runnable() {
            @Override
            public void run() {
                getAdvertisingId();
            }
        }).start();
    }

    public void fillForm(View v) {
        mUid.setText("spiderman");
        mApiKey.setText("1c915e3b5d42d05136185030892fbb846c278927");
        mAppId.setText("2070");
    }

    public void submit(View v) {

        mUid.setError(null);
        mApiKey.setError(null);
        mAppId.setError(null);

        if (!validateForm()) return;

        params.put(Util.UID_KEY, mUid.getText().toString());
        params.put(Util.APPID_KEY, mAppId.getText().toString());
        params.put(Util.TIMESTAMP_KEY, Long.toString(System.currentTimeMillis() / 1000L));
        params.put(Util.PUB0_KEY, mPub0.getText().toString());
        apiKey = mApiKey.getText().toString();
        addHashKey();
        getOffersByParams();
    }

    private boolean validateForm() {
        boolean valid = true;
        if (TextUtils.isEmpty(mUid.getText())) {
            mUid.setError(getResources().getString(R.string.empty_field_msg));
            valid = false;
        }
        if (TextUtils.isEmpty(mAppId.getText())) {
            mAppId.setError(getResources().getString(R.string.empty_field_msg));
            valid = false;
        }
        if (TextUtils.isEmpty(mApiKey.getText())) {
            mApiKey.setError(getResources().getString(R.string.empty_field_msg));
            valid = false;
        }
        return valid;
    }

    public void getAdvertisingId() {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                AdvertisingIdClient.Info idInfo = null;
                try {
                    idInfo = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext());
                } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException e) {
                    e.printStackTrace();
                }
                try {
                    googleAdId = idInfo.getId();
                    googleAdIdLimitedTrackingEnabled = idInfo.isLimitAdTrackingEnabled();
                    return true;
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            protected void onPostExecute(Boolean success) {
                if (success) {
                    params.put(Util.GOOGLE_AD_ID_KEY, googleAdId);
                    params.put(Util.GOOGLE_AD_ID_LTE_KEY, Boolean.toString(googleAdIdLimitedTrackingEnabled));
                }
            }

        }.execute();
    }

    public void addHashKey() {
        params.remove(Util.HASH_KEY);
        ArrayList<String> list = new ArrayList<>(params.keySet());
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return s.compareToIgnoreCase(t1);
            }
        });
        StringBuilder b = new StringBuilder();
        for (String param : list) {
            b.append(param).append("=").append(params.get(param)).append("&");
        }
        b.append(apiKey);

        String hashkey = hashString(b.toString());

        params.put(Util.HASH_KEY, hashkey);
    }

    public void getOffersByParams() {
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                .scheme(Util.SCHEME)
                .host(Util.HOST).addPathSegment(Util.FEED_SEG).addPathSegment(Util.VER_SEG).addPathSegment(Util.OFFERS_SEG);
        for (String key : params.keySet()) {
            urlBuilder.addQueryParameter(key, params.get(key));
        }
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainActivity.this, R.string.fail_message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    String str = response.body().string();
                    if (!hashString(str + apiKey).equals(response.header(Util.SIGNATURE_HEADER))) {
                        Toast.makeText(MainActivity.this, R.string.response_not_valid, Toast.LENGTH_SHORT).show();
                    }
                    Gson gson = new Gson();
                    FyberResponse fyberResponse = gson.fromJson(str, FyberResponse.class);
                    Intent i = new Intent(MainActivity.this, OffersActivity.class);
                    i.putExtra(OffersActivity.RESPONSE, fyberResponse);
                    startActivity(i);
                } else {
                    Gson gson = new Gson();
                    final Error error = gson.fromJson(new InputStreamReader(response.body().byteStream()), Error.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle(R.string.error_dialog_title)
                                    .setMessage(String.format(getResources().getString(R.string.error_dialog_message), error.getMessage()))
                                    .setPositiveButton(R.string.ok, null)
                                    .show();
                        }
                    });
                }
            }
        });
    }
}
