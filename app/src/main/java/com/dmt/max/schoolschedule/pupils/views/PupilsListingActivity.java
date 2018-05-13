package com.dmt.max.schoolschedule.pupils.views;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.model.pupil.Pupil;
import com.dmt.max.schoolschedule.pupils.presenters.PupilsListingPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PupilsListingActivity extends AppCompatActivity implements PupilsListingFragment.Callback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pupils_listing);
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
