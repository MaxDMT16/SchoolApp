package com.dmt.max.schoolschedule.lessons.views.details;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.groups.views.details.GroupDetailsFragment;

public class LessonDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_details);

        Fragment fragment = getFragmentToStrart();
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
    }
    private Fragment getFragmentToStrart(){
        String lessonIdKey = getResources().getString(R.string.lessonId);

        Bundle bundle = new Bundle();
        bundle.putString(lessonIdKey, getIntent().getStringExtra(lessonIdKey));
        LessonDetailsFragment fragment = new LessonDetailsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }
}
