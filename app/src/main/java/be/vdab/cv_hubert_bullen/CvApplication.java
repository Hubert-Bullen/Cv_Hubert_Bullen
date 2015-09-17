package be.vdab.cv_hubert_bullen;

import android.app.Application;

import be.vdab.cv_hubert_bullen.utils.PreferencesHelper;
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
    }
}
