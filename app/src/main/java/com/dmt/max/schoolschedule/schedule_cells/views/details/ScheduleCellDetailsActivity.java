package com.dmt.max.schoolschedule.schedule_cells.views.details;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dmt.max.schoolschedule.R;

public class ScheduleCellDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_cell_details);

        Fragment fragment = getFragmentToStrart();
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
    }

    private Fragment getFragmentToStrart() {
        String scheduleCellIdKey = getResources().getString(R.string.scheduleCellId);

        Bundle bundle = new Bundle();
        bundle.putString(scheduleCellIdKey, getIntent().getStringExtra(scheduleCellIdKey));
        ScheduleCellDetailsFragment fragment = new ScheduleCellDetailsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }
}
