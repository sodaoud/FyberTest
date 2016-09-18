package sodaoud.github.io.fyber.util;

import android.support.annotation.Nullable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by sofiane on 9/18/16.
 */

public class Util {

    public static final String UID_KEY = "uid";
    public static final String APPID_KEY = "appid";
    public static final String TIMESTAMP_KEY = "timestamp";
    public static final String PUB0_KEY = "pub0";
    public static final String FORMAT_KEY = "format";
    public static final String LOCALE_KEY = "locale";

    
    public static final String FORMAT_PARAM = "json";
    public static final String GOOGLE_AD_ID_KEY = "google_ad_id";
    public static final String GOOGLE_AD_ID_LTE_KEY = "google_ad_id_limited_tracking_enabled";
    public static final java.lang.String HASH_KEY = "hashkey";
    public static final String SCHEME = "http";
    public static final String HOST = "api.fyber.com";
    public static final String FEED_SEG = "feed";
    public static final String VER_SEG = "v1";
    public static final String OFFERS_SEG = "offers.json";
    public static final java.lang.String SIGNATURE_HEADER = "X-Sponsorpay-Response-Signature";


    @Nullable
    public static String hashString(String s) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); // should never happend
            return null;
        }
        md.update(s.getBytes());

        byte byteData[] = md.digest();

        StringBuilder hashkey = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) hashkey.append('0');
            hashkey.append(hex);
        }
        return hashkey.toString();
    }

}
