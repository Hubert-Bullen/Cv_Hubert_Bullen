package be.vdab.cv_hubert_bullen;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

import be.vdab.cv_hubert_bullen.utils.PreferencesHelper;
import be.vdab.cv_hubert_bullen.MainActivity;
import timber.log.Timber;

/**
 * Created by jeansmits on 07/09/15.
 */
public class CvApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        // Setting this back to General Info fragment after closing app.
        PreferencesHelper.setCurrentFragment(this, 0);
        setLocale(PreferencesHelper.getLanguagePreference(this));

    }

    //Language option
    public void setLocale(String lang) {

        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

    }
}
