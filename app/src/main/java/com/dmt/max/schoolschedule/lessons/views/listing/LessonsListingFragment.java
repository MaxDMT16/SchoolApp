package com.dmt.max.schoolschedule.lessons.views.listing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.SchoolApplication;
import com.dmt.max.schoolschedule.lessons.adapters.LessonsListAdapter;
import com.dmt.max.schoolschedule.lessons.presenters.listing.LessonsListingPresenter;
import com.dmt.max.schoolschedule.lessons.views.details.LessonDetailsActivity;
import com.dmt.max.schoolschedule.model.lesson.Lesson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class LessonsListingFragment extends Fragment implements LessonsListingView {
    @Inject
    LessonsListingPresenter lessonsListingPresenter;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter<LessonsListAdapter.ViewHolder> adapter;

    private List<Lesson> lessons = new ArrayList<>();

    public LessonsListingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((SchoolApplication) getActivity().getApplication()).createLessonsListingComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_lessons_listing, container, false);

        findViews(rootView);

        initLayoutReferences();

        return rootView;
    }

    private void findViews(View rootView) {
        recyclerView = rootView.findViewById(R.id.lessonsRecyclerView);
    }

    private void initLayoutReferences() {
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        adapter = new LessonsListAdapter(lessons, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add: {
                Intent groupDetailsIntent = new Intent(getContext(), LessonDetailsActivity.class);
                startActivity(groupDetailsIntent);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();

        lessonsListingPresenter.setAccessToken(getAccessToken());
        lessonsListingPresenter.setView(this);
    }

    private String getAccessToken() {
        Resources resources = getResources();

        SharedPreferences sharedPreferences = getContext().getSharedPreferences(resources.getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString(resources.getString(R.string.access_token_key), "access token");

        return accessToken;
    }

    @Override
    public void loadingStarted() {

    }

    @Override
    public void showLessons(List<Lesson> lessons) {
        this.lessons.clear();
        this.lessons.addAll(lessons);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadingFailed(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void deleteLesson(String lessonId) {
        lessonsListingPresenter.deleteLesson(lessonId);
    }

    @Override
    public void onDeleteSuccess() {
        Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteFailed(String message) {

    }

    @Override
    public void onLessonClick(String lessonId) {
        Intent pupilDetailsIntent = new Intent(getContext(), LessonDetailsActivity.class);
        pupilDetailsIntent.putExtra(getResources().getString(R.string.lessonId), lessonId);
        startActivity(pupilDetailsIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((SchoolApplication) getActivity().getApplication()).releaseLessonsListingComponent();
    }
}
