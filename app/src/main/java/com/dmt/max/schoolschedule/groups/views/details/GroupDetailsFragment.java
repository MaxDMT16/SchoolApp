package com.dmt.max.schoolschedule.groups.views.details;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.SchoolApplication;
import com.dmt.max.schoolschedule.groups.presenters.details.GroupDetailsPresenter;
import com.dmt.max.schoolschedule.model.group.Group;
import com.dmt.max.schoolschedule.model.group.responses.GroupResponse;

import javax.inject.Inject;

public class GroupDetailsFragment extends Fragment implements GroupDetailsView{
    @Inject
    GroupDetailsPresenter groupDetailsPresenter;

    TextInputEditText groupName;
    Button actionButton;

    private Group group;

    public GroupDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((SchoolApplication) getActivity().getApplication()).createGroupDetailsComponent().inject(this);

        group = new Group();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_group_details, container, false);

        findViews(rootView);

        initListeners();

        return rootView;
    }

    private void initListeners() {
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGroupFromUI();
                groupDetailsPresenter.onActionButtonClick(group);
            }
        });
    }

    private void setGroupFromUI() {
        group.setName(groupName.getText().toString());
    }

    private void findViews(View view) {
        groupName = view.findViewById(R.id.groupNameEditText);

        actionButton = view.findViewById(R.id.groupActionButton);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        groupDetailsPresenter.setView(this);

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateGroupSuccess() {
        getActivity().finish();
    }

    @Override
    public void onUpdateGroupSuccess() {
        getActivity().finish();
    }

    @Override
    public void onRequestFail(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestGroupByIdSuccess(GroupResponse groupResponse) {
        setGroup(groupResponse);
        fillEditTextWithGroupData(groupResponse);
        changeButtonTextToUpdate();
    }

    private void changeButtonTextToUpdate() {
        actionButton.setText(getResources().getString(R.string.update));
    }

    private void fillEditTextWithGroupData(GroupResponse groupResponse) {
        groupName.setText(groupResponse.getName());
    }

    private void setGroup(GroupResponse groupResponse) {
        group.setName(groupResponse.getName());
        group.setId(groupResponse.getId());
    }

    @Override
    public String getAccessToken() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString(getResources().getString(R.string.access_token_key), "access token");

        return accessToken;
    }

    @Override
    public String getRefreshToken() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        String refreshToken = sharedPreferences.getString(getResources().getString(R.string.refresh_token_key), "refresh token");

        return refreshToken;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((SchoolApplication) getActivity().getApplication()).releaseGroupDetailsComponent();
    }
}
