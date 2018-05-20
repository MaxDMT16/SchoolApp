package com.dmt.max.schoolschedule.pupils.views.details;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dmt.max.schoolschedule.R;

public class PupilDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pupil_details);

        Fragment fragment = getFragmentToStrart();
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
    }

    private Fragment getFragmentToStrart(){
        String pupilIdKey = getResources().getString(R.string.pupilId);

        Bundle bundle = new Bundle();
        bundle.putString(pupilIdKey, getIntent().getStringExtra(pupilIdKey));
        PupilDetailsFragment fragment = new PupilDetailsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }
}
