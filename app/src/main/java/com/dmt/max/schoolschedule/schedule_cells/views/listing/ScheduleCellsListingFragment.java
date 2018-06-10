package com.dmt.max.schoolschedule.schedule_cells.views.listing;

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
import com.dmt.max.schoolschedule.model.schedule_cell.ScheduleCell;
import com.dmt.max.schoolschedule.schedule_cells.adapters.ScheduleCellsListAdapter;
import com.dmt.max.schoolschedule.schedule_cells.presenters.listing.ScheduleCellsListingPresenter;
import com.dmt.max.schoolschedule.schedule_cells.views.details.ScheduleCellDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ScheduleCellsListingFragment extends Fragment implements ScheduleCellsListingView {
    @Inject
    ScheduleCellsListingPresenter scheduleCellsListingPresenter;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter<ScheduleCellsListAdapter.ViewHolder> adapter;

    private List<ScheduleCell> scheduleCells = new ArrayList<>();

    public ScheduleCellsListingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((SchoolApplication) getActivity().getApplication()).createScheduleCellsListingComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_schedule_cells_listing, container, false);

        findViews(rootView);

        initLayoutReferences();

        return rootView;
    }

    private void initLayoutReferences() {
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        adapter = new ScheduleCellsListAdapter(scheduleCells, this);
        recyclerView.setAdapter(adapter);
    }

    private void findViews(View view) {
        recyclerView = view.findViewById(R.id.cellsRecyclerView);
    }

    @Override
    public void onResume() {
        super.onResume();

        scheduleCellsListingPresenter.setAccessToken(getAccessToken());
        scheduleCellsListingPresenter.setView(this);
    }

    private String getAccessToken() {
        Resources resources = getResources();

        SharedPreferences sharedPreferences = getContext().getSharedPreferences(resources.getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString(resources.getString(R.string.access_token_key), "access token");

        return accessToken;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add: {
                Intent groupDetailsIntent = new Intent(getContext(), ScheduleCellDetailsActivity.class);
                startActivity(groupDetailsIntent);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadingStarted() {

    }

    @Override
    public void showScheduleCells(List<ScheduleCell> scheduleCells) {
        this.scheduleCells.clear();
        this.scheduleCells.addAll(scheduleCells);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadingFailed(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteScheduleCell(String scheduleCellId) {
        scheduleCellsListingPresenter.deleteScheduleCell(scheduleCellId);
    }

    @Override
    public void onDeleteSuccess() {
        Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteFailed(String message) {

    }

    @Override
    public void onScheduleCellClick(String scheduleCellId) {
        Intent scheduleCellDetailsIntent = new Intent(getContext(), ScheduleCellDetailsActivity.class);
        scheduleCellDetailsIntent.putExtra(getResources().getString(R.string.scheduleCellId), scheduleCellId);
        startActivity(scheduleCellDetailsIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((SchoolApplication) getActivity().getApplication()).releaseScheduleCellsListingComponent();
    }
}
