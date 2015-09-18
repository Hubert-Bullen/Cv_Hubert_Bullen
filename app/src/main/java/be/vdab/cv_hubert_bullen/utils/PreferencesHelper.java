package be.vdab.cv_hubert_bullen.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by RWIL on 01/09/2015.
 */
public class PreferencesHelper {

    private final static String PREF_LANGUAGE = "language";

    private final static Integer CURRENT_FRAGMENT = 0;

    private final static String LANGUAGE_NL = "nl";
    private final static String LANGUAGE_EN = "en";
    private final static String LANGUAGE_JP = "jp";

    private final static String FRAGMENT = "Home";

    //Retrieve push enabled (default = true)
    public static String getLanguagePreference(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(PREF_LANGUAGE, LANGUAGE_NL);
    }

    //Enable or disable push messages
    public static void setLanguagePreference(Context context, String language){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_LANGUAGE, language);
        editor.apply();
    }

    public static Integer getCurrentFragment(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(FRAGMENT, CURRENT_FRAGMENT);
    }

    public static void setCurrentFragment(Context context, Integer position){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(FRAGMENT, position);
        editor.apply();
    }

}
