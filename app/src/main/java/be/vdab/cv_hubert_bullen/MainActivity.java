package be.vdab.cv_hubert_bullen;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Locale;

import be.vdab.cv_hubert_bullen.fragments.FragmentFiveHobbies;
import be.vdab.cv_hubert_bullen.fragments.FragmentFourLanguages;
import be.vdab.cv_hubert_bullen.fragments.FragmentOneGeneralInfo;
import be.vdab.cv_hubert_bullen.fragments.FragmentSixWorkingExp;
import be.vdab.cv_hubert_bullen.fragments.FragmentThreeITKnowledge;
import be.vdab.cv_hubert_bullen.fragments.FragmentTwoStudies;
import be.vdab.cv_hubert_bullen.utils.CustomNavBarListAdapter;
import be.vdab.cv_hubert_bullen.utils.PreferencesHelper;

public class MainActivity extends AppCompatActivity {

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private String[] titles;
    Integer[] icons;

    private final static String LANGUAGE_NL = "nl";
    private final static String LANGUAGE_EN = "en";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Nav Drawer icons
        icons = new Integer[]{
                R.drawable.account,
                R.drawable.book_open,
                R.drawable.responsive,
                R.drawable.comment,
                R.drawable.google_controller,
                R.drawable.mouse_variant
        };

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Starting with last fragment or when starting up with GeneralInfo Fragment
        titles = getResources().getStringArray(R.array.navBar_array);
        Fragment fr = getItem(PreferencesHelper.getCurrentFragment(this));
        getSupportActionBar().setTitle(titles[PreferencesHelper.getCurrentFragment(this)]);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fr);
        fragmentTransaction.commit();

        // Nav Drawer Set up
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        setupDrawer();

        mDrawerList = (ListView)findViewById(R.id.nav_list);
        addDrawerItems();

        //Nav Drawer on click.
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDrawerLayout.closeDrawers();
                Fragment fr;
                fr = getItem(position);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fr);
                fragmentTransaction.commit();
                PreferencesHelper.setCurrentFragment(MainActivity.this,position);
            }
        });

    }
    // drawer + content
    public Fragment getItem(int position) {
        mActivityTitle = titles[position];
        switch (position) {
            case 0: // Fragment # 0 - This will show FragmentOne
                return FragmentOneGeneralInfo.createNewFragmentOne();
            case 1:
                return FragmentTwoStudies.createNewFragmentTwo();
            case 2: //TODO: implement Frag3
                return FragmentThreeITKnowledge.createNewFragmentThree();
            case 3://TODO: implement Frag4
                return FragmentFourLanguages.createNewFragmentFour();
            case 4://TODO: implement Frag5
                return FragmentFiveHobbies.createNewFragmentFive();
            case 5://TODO: implement Frag6
                return FragmentSixWorkingExp.createNewFragmentSix();
            default:
                return null;
        }
    }

    // left button on toolbar for drawer
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // options menu
        if (id == R.id.radio_nl){
            PreferencesHelper.setLanguagePreference(this, LANGUAGE_NL);
            setLocale(LANGUAGE_NL);
        } else if (id == R.id.radio_en) {
            PreferencesHelper.setLanguagePreference(this, LANGUAGE_EN);
            setLocale(LANGUAGE_EN);
        }
        // drawer
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    // Drawer setup
    private void addDrawerItems() {
        String[] osArray = getResources().getStringArray(R.array.navBar_array) ;
        //mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mAdapter = new CustomNavBarListAdapter(this, osArray, icons);
        mDrawerList.setAdapter(mAdapter);
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(getResources().getString(R.string.action_settings));
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    //Adding option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Language option
    public void setLocale(String lang) {

        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

        recreate();
    }

}
