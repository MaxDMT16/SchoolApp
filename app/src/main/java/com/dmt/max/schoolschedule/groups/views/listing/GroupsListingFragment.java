package com.dmt.max.schoolschedule.groups.views.listing;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.SchoolApplication;
import com.dmt.max.schoolschedule.groups.adapters.GroupsListAdapter;
import com.dmt.max.schoolschedule.groups.presenters.listing.GroupsListingPresenter;
import com.dmt.max.schoolschedule.model.group.Group;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class GroupsListingFragment extends Fragment implements GroupsListingView {
    @Inject
    GroupsListingPresenter groupsListingPresenter;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Group> groups = new ArrayList<>();

    public GroupsListingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((SchoolApplication) getActivity().getApplication()).createGroupsListingComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_groups_listing, container, false);

        findViews(rootView);

        initLayoutReferences();

        return rootView;
    }

    private void initLayoutReferences() {
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        adapter = new GroupsListAdapter(groups, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        groupsListingPresenter.setAccessToken(getAccessToken());
        groupsListingPresenter.setView(this);
    }

    private void findViews(View view) {
        recyclerView = view.findViewById(R.id.groupsRecyclerView);
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
    public void showGroups(List<Group> groups) {
        this.groups.clear();
        this.groups.addAll(groups);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadingFailed(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteGroup(String groupId) {
        groupsListingPresenter.deleteGroup(groupId);
    }

    @Override
    public void onDeleteSuccess() {
        Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteFailed(String message) {

    }

    @Override
    public void onGroupClick(String groupId) {
        Toast.makeText(getContext(), "Not implemented", Toast.LENGTH_SHORT).show();
    }
}
