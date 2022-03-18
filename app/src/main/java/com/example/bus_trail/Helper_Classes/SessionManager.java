package com.example.bus_trail.Helper_Classes;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences userSession;
    SharedPreferences.Editor editor;
    Context context;


    // Login Session Keys
    public static final String TYPE_LOGINSESSION = "LoginSession";
    public static final String IS_LOGIN = "isLoggedIn";
    public static final String KEY_EMAIL = "userEmail";
    public static final String KEY_PASSWORD = "userPassword";
    public static final String KEY_FIRST_TIME = "firstTime";

    // User Details Session Keys
    public static final String TYPE_USER_DATA_SESSION = "userDataSession";
    public static final String KEY_FULLNAME = "userFullname";
    public static final String KEY_USERNAME = "userName";
    public static final String KEY_UID = "uid";
    public static final String KEY_PH_NO = "phoneNumber";
    public static final String KEY_USER_IMAGE_URI = "userImageURI";


    // FCM Token Key
    public static final String FCM_TOKEN = "FCM-Token";

    // Preferences Keys
    public static final String TYPE_PREFERENCES_SESSION = "userPreferencesSession";
    public static final String KEY_PREFERENCE_STORY_POETRY = "story&poetrySession";

    // Setting Session Keys
    public static final String APP_LANGUAGE = "currentLanguage";




    public SessionManager(Context _context, String Type) {
        context = _context;
        userSession = context.getSharedPreferences(Type,Context.MODE_PRIVATE);
    }

    public void setFirstTimeUser() {
        editor = userSession.edit();
        editor.putBoolean(KEY_FIRST_TIME,false);
        editor.apply();
    }

    public boolean isFirstTime() {
        return userSession.getBoolean(KEY_FIRST_TIME,true);
    }

    // For Notification
    public void setToken(String token){
        editor = userSession.edit();

        editor.putString(FCM_TOKEN,token);

        editor.apply();
    }

    public String getFcmToken() {
        return userSession.getString(FCM_TOKEN,"");
    }


    public void updateLanguage(String Language) {
        editor = userSession.edit();

        editor.putString(APP_LANGUAGE,Language);

        editor.apply();
    }

    // For App Lang
    public String getCurrentAppLanguage() {
        return userSession.getString(APP_LANGUAGE,"");
    }



    // Login
    public void CreateLoginSession(String email,String password) {
        editor = userSession.edit();

        editor.putBoolean(IS_LOGIN,true);

        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_PASSWORD,password);

        editor.apply();
    }
    public HashMap<String,String> getUserLoginDetails() {
        HashMap<String,String> userDetails = new HashMap<String, String>();

        userDetails.put(KEY_EMAIL,userSession.getString(KEY_EMAIL,null));
        userDetails.put(KEY_PASSWORD,userSession.getString(KEY_PASSWORD,null));

        return userDetails;
    }

    public void CreateUserDetailsSession(String fullName, String uid, String userName, String userImage,String phNo) {
        editor = userSession.edit();

        if(fullName != null)
            editor.putString(KEY_FULLNAME,fullName);
        if(userName != null)
            editor.putString(KEY_USERNAME,userName);
        if(uid != null)
            editor.putString(KEY_UID,uid);
        if(userImage != null)
            editor.putString(KEY_USER_IMAGE_URI,userImage);
        if(phNo != null)
            editor.putString(KEY_PH_NO,phNo);

        editor.apply();
    }
//    public void CreateUserDetailsSession(User_Model model) {
//        editor = userSession.edit();
//
//        if(model.getFullname() != null)
//            editor.putString(KEY_FULLNAME,model.getFullname());
//        if(model.getUsername() != null)
//            editor.putString(KEY_USERNAME,model.getUsername());
//        if(model.getUid() != null)
//            editor.putString(KEY_UID,model.getUid());
//        if(model.getUserphotourl() != null)
//            editor.putString(KEY_USER_IMAGE_URI,model.getUserphotourl());
//        if(model.getPhonenumber() != null)
//            editor.putString(KEY_PH_NO,model.getPhonenumber());
//
//        editor.apply();
//    }
//
//
//    public HashMap<String,String> getUserDetails() {
//        HashMap<String,String> userDetails = new HashMap<String, String>();
//
//        userDetails.put(KEY_FULLNAME,userSession.getString(KEY_FULLNAME,null));
//        userDetails.put(KEY_UID,userSession.getString(KEY_UID,null));
//        userDetails.put(KEY_USERNAME,userSession.getString(KEY_USERNAME,null));
//        userDetails.put(KEY_USER_IMAGE_URI,userSession.getString(KEY_USER_IMAGE_URI,null));
//        userDetails.put(KEY_PH_NO,userSession.getString(KEY_PH_NO,null));
//
//
//        return userDetails;
//    }
//
//    public void CreateUserPreferenceSession(String Prefs) {
//        editor = userSession.edit();
//
//        if(Prefs != null)
//            editor.putString(KEY_PREFERENCE_STORY_POETRY,Prefs);
//
//        editor.apply();
//    }
//    public String getUserPreference() {
//        return userSession.getString(KEY_PREFERENCE_STORY_POETRY,null);
//    }
//
//
//    public boolean isLoggedIn() {
//        return userSession.getBoolean(IS_LOGIN,false);
//    }
//
//    public void clearUserSession(){
//        userSession = context.getSharedPreferences(TYPE_LOGINSESSION,Context.MODE_PRIVATE);
//        editor = userSession.edit();
//        editor.clear();
//        editor.apply();
//        userSession = context.getSharedPreferences(TYPE_USER_DATA_SESSION,Context.MODE_PRIVATE);
//        editor = userSession.edit();
//        editor.clear();
//        editor.apply();
//        userSession = context.getSharedPreferences(TYPE_PREFERENCES_SESSION,Context.MODE_PRIVATE);
//        editor = userSession.edit();
//        editor.clear();
//        editor.apply();
//    }
}
