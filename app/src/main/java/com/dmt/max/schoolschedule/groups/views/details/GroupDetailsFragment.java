package com.dmt.max.schoolschedule.groups.views.details;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dmt.max.schoolschedule.R;

public class GroupDetailsFragment extends Fragment implements GroupDetailsView{
    TextInputEditText groupName;
    Button actionButton;

    public GroupDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_group_details, container, false);

        findViews(rootView);

        return rootView;
    }

    private void findViews(View view) {
        groupName = view.findViewById(R.id.groupNameEditText);

        actionButton = view.findViewById(R.id.groupActionButton);
    }
}
