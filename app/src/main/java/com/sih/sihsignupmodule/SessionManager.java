package com.sih.sihsignupmodule;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;


    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    public static final String PREF_NAME = "SmartRationCard";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";


    public static final String USERFIRSTNAME = "firstname";
    public static final String USERMIDDLENAME = "middlename";
    public static final String USERLASTNAME = "lastname";
    public static final String USERCONTACT = "contact";
    public static final String USEREMAIL = "email";
    public static final String USERRATIONCARDNUMBER = "rationcardnumber";
    public static final String USERADHARCARDNUMBER = "adharcardnumber";
    public static final String USERPANCARDNUMBER = "pancardnumber";




   public static String name = null;

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String firstname, String middlename, String lastname , String contact, String email , String rationcardnumber, String adharcardnumber , String pancardnumber){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(USERFIRSTNAME, firstname);
        editor.putString(USERMIDDLENAME, middlename);
        editor.putString(USERLASTNAME, lastname);
        editor.putString(USERCONTACT, contact);
        editor.putString(USEREMAIL, email);
        editor.putString(USERRATIONCARDNUMBER, rationcardnumber);
        editor.putString(USERADHARCARDNUMBER, adharcardnumber);
        editor.putString(USERPANCARDNUMBER, pancardnumber);


        // commit changes
        editor.commit();

    }



    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
 /*   public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, Login.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }

*/


        /**
         * Get stored session data
         * */
    public HashMap<String, String> getUserDetails()
    {
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(USERFIRSTNAME, pref.getString(USERFIRSTNAME, null));
        user.put(USERMIDDLENAME, pref.getString(USERMIDDLENAME, null));
        user.put(USERLASTNAME, pref.getString(USERLASTNAME,null));
        user.put(USERCONTACT, pref.getString(USERCONTACT, null));
        user.put(USEREMAIL, pref.getString(USEREMAIL, null));
        user.put(USERRATIONCARDNUMBER, pref.getString(USERRATIONCARDNUMBER, null));
        user.put(USERADHARCARDNUMBER, pref.getString(USERADHARCARDNUMBER, null));
        user.put(USERPANCARDNUMBER, pref.getString(USERPANCARDNUMBER, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){

        return pref.getBoolean(IS_LOGIN, false);
    }
}
