package com.dmt.max.schoolschedule.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.SchoolApplication;
import com.dmt.max.schoolschedule.groups.views.listing.GroupsListingActivity;
import com.dmt.max.schoolschedule.network.SchoolSystemWebService;
import com.dmt.max.schoolschedule.pupils.views.listing.PupilsListingActivity;
import com.dmt.max.schoolschedule.teachers.views.listing.TeachersListingActivity;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    SchoolSystemWebService schoolSystemWebService;

    Button pupilsButton;
    Button groupsButton;
    Button teachersButton;

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
        groupsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GroupsListingActivity.class);
                startActivity(intent);
            }
        });

        teachersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TeachersListingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findViews() {
        pupilsButton = findViewById(R.id.buttonPupils);
        groupsButton = findViewById(R.id.buttonGroups);
        teachersButton = findViewById(R.id.buttonTeachers);
    }
}
