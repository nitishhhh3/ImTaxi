package iamtaxi.dmi.com.imtaxi.utill;

import android.content.Context;
import android.content.SharedPreferences;

public class ImTaxtPrefs {


    private static SharedPreferences mPreferences;
    private static ImTaxtPrefs mInstance;
    private static SharedPreferences.Editor mEditor;
    private static Context mContext;
    private static final String TAG_LOGGED_IN = "loogedIn";
    private static final String TAG_USER_ID = "user_id";
    private static final String TAG_USER_TYPE = "user_type";


    public static ImTaxtPrefs getInstance(Context context) {
        if (mInstance == null) {
            mContext = context;
            mInstance = new ImTaxtPrefs();
            mPreferences = mContext.getSharedPreferences("imtaxi", Context.MODE_PRIVATE);
            mEditor = mPreferences.edit();
        }
        return mInstance;
    }


    public boolean isLoggedIn() {
        return mPreferences.getBoolean(TAG_LOGGED_IN, false);
    }


    public void setLoggedIn(boolean loggedIn) {
        mEditor.putBoolean(TAG_LOGGED_IN, loggedIn);
        mEditor.apply();
    }

    public String getUserId() {
        return mPreferences.getString(TAG_USER_ID, null);
    }


    public void setUserId(String userId) {
        mEditor.putString(TAG_USER_ID, userId);
        mEditor.apply();
    }

    public String getUserType() {
        return mPreferences.getString(TAG_USER_TYPE, null);
    }


    public void setUserType(String userType) {
        mEditor.putString(TAG_USER_TYPE, userType);
        mEditor.apply();
    }

}
