package com.dmt.max.schoolschedule.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dmt.max.schoolschedule.PupilsListingActivityTest;
import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.SchoolApplication;
import com.dmt.max.schoolschedule.network.SchoolSystemWebService;
import com.dmt.max.schoolschedule.pupils.views.PupilsListingActivity;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    SchoolSystemWebService schoolSystemWebService;

    Button pupilsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((SchoolApplication) getApplication()).getSchoolComponent().inject(this);

        findViews();

        pupilsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PupilsListingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findViews() {
        pupilsButton = findViewById(R.id.buttonPupils);
    }
}
