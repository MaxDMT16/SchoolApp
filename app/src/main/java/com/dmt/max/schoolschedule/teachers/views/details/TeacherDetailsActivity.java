package com.dmt.max.schoolschedule.teachers.views.details;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dmt.max.schoolschedule.R;

public class TeacherDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details);

        Fragment fragment = getFragmentToStrart();
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
    }

    private Fragment getFragmentToStrart(){
        String teacherIdKey = getResources().getString(R.string.teacherId);

        Bundle bundle = new Bundle();
        bundle.putString(teacherIdKey, getIntent().getStringExtra(teacherIdKey));
        TeacherDetailsFragment fragment = new TeacherDetailsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }
}
