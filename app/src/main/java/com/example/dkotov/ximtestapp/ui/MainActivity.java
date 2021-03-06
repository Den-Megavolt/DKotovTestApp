package com.example.dkotov.ximtestapp.ui;

import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dkotov.ximtestapp.R;
import com.example.dkotov.ximtestapp.model.DataItem;

public class MainActivity extends AppCompatActivity implements
        CatsFragment.OnListFragmentInteractionListener,
        DogsFragment.OnListFragmentInteractionListener,
DetailsFragment.OnFragmentInteractionListener{

    private static final String TAG = "XimTestApp: " + MainActivity.class.getSimpleName();
    private static final String SELECTED_TAB = "selected tab";

    public static final String IMG_URL = "url";
    public static final String TEXT_CONTENT = "content";

    private FragmentTransaction mFragmentTransaction;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = (TabLayout) findViewById(R.id.navigation);
        mTabLayout.addOnTabSelectedListener(onTabSelectedListener);

        mTabLayout.getTabAt(PreferenceManager.getDefaultSharedPreferences(this)
                .getInt(SELECTED_TAB, 0)).select();
    }

    private TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition()) {
                case 0:
                    Log.d(TAG, "Selected tab: " + tab.getPosition());
                    mFragmentTransaction = getSupportFragmentManager().beginTransaction();
                    mFragmentTransaction.replace(R.id.content, CatsFragment.newInstance());
                    mFragmentTransaction.commit();
                    break;
                case 1:
                    Log.d(TAG, "Selected tab: " + tab.getPosition());
                    mFragmentTransaction = getSupportFragmentManager().beginTransaction();
                    mFragmentTransaction.replace(R.id.content, DogsFragment.newInstance());
                    mFragmentTransaction.commit();
                    break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            switch (tab.getPosition()) {
                case 0:
                    Log.d(TAG, "Selected tab: " + tab.getPosition());
                    mFragmentTransaction = getSupportFragmentManager().beginTransaction();
                    mFragmentTransaction.replace(R.id.content, CatsFragment.newInstance());
                    mFragmentTransaction.commit();
                    break;
                case 1:
                    Log.d(TAG, "Selected tab: " + tab.getPosition());
                    mFragmentTransaction = getSupportFragmentManager().beginTransaction();
                    mFragmentTransaction.replace(R.id.content, DogsFragment.newInstance());
                    mFragmentTransaction.commit();
                    break;
            }
        }
    };

    @Override
    public void onListFragmentInteraction(DataItem item) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "Backbutton pressed: ");
        finish();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPauseCalled, saving state to SharedPrefs: "
                + mTabLayout.getSelectedTabPosition());
        PreferenceManager.getDefaultSharedPreferences(this)
                .edit().putInt(SELECTED_TAB, mTabLayout.getSelectedTabPosition()).apply();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "OnStop called");
    }
}
