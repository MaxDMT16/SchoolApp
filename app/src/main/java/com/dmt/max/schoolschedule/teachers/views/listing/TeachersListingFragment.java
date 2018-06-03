package com.dmt.max.schoolschedule.teachers.views.listing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
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
import com.dmt.max.schoolschedule.model.teachers.Teacher;
import com.dmt.max.schoolschedule.teachers.adapters.TeachersListAdapter;
import com.dmt.max.schoolschedule.teachers.presenters.listing.TeachersListingPresenter;
import com.dmt.max.schoolschedule.teachers.views.details.TeacherDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TeachersListingFragment extends Fragment implements TeachersListingView {
    @Inject
    TeachersListingPresenter teachersListingPresenter;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Teacher> teachers = new ArrayList<>();

    public TeachersListingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((SchoolApplication) getActivity().getApplication()).createTeachersListingComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_teachers_listing, container, false);

        findViews(rootView);

        initLayoutReferences();

        return rootView;
    }

    private void initLayoutReferences() {
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        adapter = new TeachersListAdapter(teachers, this);
        recyclerView.setAdapter(adapter);
    }

    private void findViews(View rootView) {
        recyclerView = rootView.findViewById(R.id.teachersRecyclerView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add: {
                Intent teacherDetailsIntent = new Intent(getContext(), TeacherDetailsActivity.class);
                startActivity(teacherDetailsIntent);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();

        teachersListingPresenter.setAccessToken(getAccessToken());
        teachersListingPresenter.setView(this);

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
    public void showTeachers(List<Teacher> teachers) {
        this.teachers.clear();
        this.teachers.addAll(teachers);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadingFailed(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteTeacher(String teacherId) {
        teachersListingPresenter.deleteTeacher(teacherId);
    }

    @Override
    public void onDeleteSuccess() {
        Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteFailed(String message) {

    }

    @Override
    public void onTeacherClick(String teacherId) {
        Intent pupilDetailsIntent = new Intent(getContext(), TeacherDetailsActivity.class);
        pupilDetailsIntent.putExtra(getResources().getString(R.string.teacherId), teacherId);
        startActivity(pupilDetailsIntent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ((SchoolApplication) getActivity().getApplication()).releaseTeachersListingComponent();
    }
}
