package com.dmt.max.schoolschedule.groups.views.details;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dmt.max.schoolschedule.R;

public class GroupDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);

        Fragment fragment = getFragmentToStrart();
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
    }


    private Fragment getFragmentToStrart(){
        String groupIdKey = getResources().getString(R.string.groupId);

        Bundle bundle = new Bundle();
        bundle.putString(groupIdKey, getIntent().getStringExtra(groupIdKey));
        GroupDetailsFragment fragment = new GroupDetailsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }
}
