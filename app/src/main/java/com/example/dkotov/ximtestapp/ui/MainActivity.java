package com.example.dkotov.ximtestapp.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dkotov.ximtestapp.R;
import com.example.dkotov.ximtestapp.XimTestApp;
import com.example.dkotov.ximtestapp.model.DataItem;

public class MainActivity extends AppCompatActivity implements
        CatsFragment.OnListFragmentInteractionListener,
        DogsFragment.OnListFragmentInteractionListener,
DetailsFragment.OnFragmentInteractionListener{

    private static final String TAG = "XimTestApp: " + MainActivity.class.getSimpleName();

    public static final String IMG_URL = "url";
    public static final String TEXT_CONTENT = "content";

    private FragmentTransaction fragmentTransaction;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.navigation);
        tabLayout.addOnTabSelectedListener(onTabSelectedListener);

        if (XimTestApp.getStartCounter() == 0) {
            Log.d(TAG, "Start counter: " + XimTestApp.getStartCounter());
            tabLayout.getTabAt(0).select();
            XimTestApp.setStartCounter(1);
        }

    }

    private TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition()) {
                case 0:
                    Log.d(TAG, "Selected tab: " + tab.getPosition());
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, CatsFragment.newInstance());
                    fragmentTransaction.commit();
                    break;
                case 1:
                    Log.d(TAG, "Selected tab: " + tab.getPosition());
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, DogsFragment.newInstance());
                    fragmentTransaction.commit();
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
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, CatsFragment.newInstance());
                    fragmentTransaction.commit();
                    break;
                case 1:
                    Log.d(TAG, "Selected tab: " + tab.getPosition());
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, DogsFragment.newInstance());
                    fragmentTransaction.commit();
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
        Log.d(TAG, "Backbutton pressed: " + XimTestApp.getStartCounter());
        XimTestApp.setStartCounter(0);
        finish();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "OnStop called");
    }
}
