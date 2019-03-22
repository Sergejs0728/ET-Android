package com.emptytrip.hr.emptytrip;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Inder on 2018-03-23.
 */

public class DataManager {

    Context context;
    SharedPreferences sharedPreferences;

    public DataManager(Context context) {
        this.context = context;
        getSharedPreferences();
    }

    private void getSharedPreferences() {
        sharedPreferences = context.getSharedPreferences(Config.DATA_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void setPreference(String dataKey, Object dataValue) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (dataValue instanceof Boolean) {

            Boolean val = (Boolean) dataValue;
            editor.putBoolean(dataKey, val);

        } else if (dataValue instanceof String) {

            String val = (String) dataValue;
            editor.putString(dataKey, val);

        } else if (dataValue instanceof Integer) {

            int val = (int) dataValue;
            editor.putInt(dataKey, val);

        } else if (dataValue instanceof Float) {

            Float val = (Float) dataValue;
            editor.putFloat(dataKey, val);
        }

        editor.apply();
    }

    public Object getPreference(String dataKey, String dataType) {

        if (dataType.equals("BOOL")) {
            return sharedPreferences.getBoolean(dataKey, false);
        } else if (dataType.equals("INT")) {
            return sharedPreferences.getInt(dataKey, 0);
        } else if (dataType.equals("FLOAT")) {
            return sharedPreferences.getFloat(dataKey, 0);
        } else if (dataType.equals("STRING")) {
            return sharedPreferences.getString(dataKey, "");
        } else {
            return null;
        }
    }

    // Intro Screen Methods
    public boolean introScreenShown() {
        return sharedPreferences.getBoolean(Config.DataProgress.INTRO_SCREEN_SHOWN.name(), false);
    }

    public void setIntroScreenShown() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Config.DataProgress.INTRO_SCREEN_SHOWN.name(), true);
        editor.apply();
    }

    public void setIntroScreenNotShown() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Config.DataProgress.INTRO_SCREEN_SHOWN.name(), false);
        editor.apply();
    }


    // Registration Methods
    public boolean registrationComplete() {
        return sharedPreferences.getBoolean(Config.DataProgress.REGISTRATION_COMPLETE.name(), false);
    }

    public void setRegistrationComplete() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Config.DataProgress.REGISTRATION_COMPLETE.name(), true);
        editor.apply();
    }

    public String registrationStepDone() {
        return sharedPreferences.getString(Config.DataProgress.REGISTRATION_STEP_DONE.name(), "");
    }

    public void setRegistrationStepDone(String dataValue) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Config.DataProgress.REGISTRATION_STEP_DONE.name(), dataValue);
        editor.apply();
    }

    public void removeRegistrationData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(Config.DataProgress.REGISTRATION_STEP_DONE.name());
        editor.remove(Config.DataProgress.REGISTRATION_COMPLETE.name());
        editor.remove(Config.DataProgress.SIGNIN_COMPLETE.name());

        editor.apply();
    }

    // Sign In Methods
    public boolean signInComplete() {
        return sharedPreferences.getBoolean(Config.DataProgress.SIGNIN_COMPLETE.name(), false);
    }

    public void setSignInComplete() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Config.DataProgress.SIGNIN_COMPLETE.name(), true);
        editor.apply();
    }
    

    public void setEmailOAuthData(String service, String token, String xoauth) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("OAUTH_SERVICE", service);
        editor.putString("OAUTH_TOKEN", token);
        editor.putString("YAHOO_XOAUTH", xoauth);
        editor.apply();
    }

    public OAuthModel emailOAuthData() {
        OAuthModel oa = new OAuthModel();

        oa.setService(sharedPreferences.getString("OAUTH_SERVICE", ""));
        oa.setToken(sharedPreferences.getString("OAUTH_TOKEN", ""));
        oa.setXoauth(sharedPreferences.getString("YAHOO_XOAUTH", ""));

        return oa;
    }
}
