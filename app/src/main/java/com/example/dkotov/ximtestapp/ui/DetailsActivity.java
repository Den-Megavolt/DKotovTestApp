package com.example.dkotov.ximtestapp.ui;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dkotov.ximtestapp.R;

public class DetailsActivity extends AppCompatActivity implements DetailsFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
