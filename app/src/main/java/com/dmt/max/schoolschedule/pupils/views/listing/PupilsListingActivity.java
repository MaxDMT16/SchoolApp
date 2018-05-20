package com.dmt.max.schoolschedule.pupils.views.listing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.model.pupil.Pupil;

public class PupilsListingActivity extends AppCompatActivity implements PupilsListingFragment.Callback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pupils_listing);
        setToolbar();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.app_name);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pupils_menu, menu);

        return true;
    }

    @Override
    public void onPupilsLoaded(Pupil pupil) {
        Toast.makeText(this, "Pupils loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPupilClicked(Pupil pupil) {
        String pupilFullName = pupil.getFirstName() + pupil.getLastName();
        Toast.makeText(this, "Click on " + pupilFullName, Toast.LENGTH_SHORT).show();
    }
}
