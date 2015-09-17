package be.vdab.cv_hubert_bullen;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import be.vdab.cv_hubert_bullen.fragments.FragmentOneGeneralInfo;
import be.vdab.cv_hubert_bullen.fragments.FragmentThreeITKnowledge;
import be.vdab.cv_hubert_bullen.fragments.FragmentTwoStudies;
import be.vdab.cv_hubert_bullen.utils.CustomNavBarListAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private String[] titles;
    Integer[] icons;



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

        // Starting with GeneralInfo Fragment
        Fragment fr = FragmentOneGeneralInfo.createNewFragmentOne();
        titles= getResources().getStringArray(R.array.navBar_array);
        getSupportActionBar().setTitle(titles[0]);
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
            }
        });

    }
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
                return null;
            case 4://TODO: implement Frag5
                return null;
            case 5://TODO: implement Frag6
                return null;
            default:
                return null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

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
                getSupportActionBar().setTitle("Options");
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
}
