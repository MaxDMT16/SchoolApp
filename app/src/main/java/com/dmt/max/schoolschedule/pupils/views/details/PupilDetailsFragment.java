package com.dmt.max.schoolschedule.pupils.views.details;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.SchoolApplication;
import com.dmt.max.schoolschedule.model.group.Group;
import com.dmt.max.schoolschedule.model.group.responses.GroupsResponse;
import com.dmt.max.schoolschedule.model.pupil.Pupil;
import com.dmt.max.schoolschedule.model.pupil.resoponses.PupilResponse;
import com.dmt.max.schoolschedule.pupils.presenters.details.PupilDetailsPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PupilDetailsFragment extends Fragment implements PupilDetailsView {
    @Inject
    PupilDetailsPresenter pupilDetailsPresenter;

    private TextInputEditText pupilLastNameEditText;
    private TextInputEditText pupilFirstNameEditText;
    private Spinner groupsSpinner;

    private Button createPupilButton;

    private Pupil pupil;
    private List<Group> groups;
    private ArrayAdapter<Group> groupsSpinnerAdapter;

    public PupilDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((SchoolApplication) getActivity().getApplication()).createPupilDetailsComponent().inject(this);

        pupil = new Pupil();
        groups = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_pupil_details, container, false);

        findViews(rootView);

        initViews();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pupilDetailsPresenter.setView(this);

        Bundle arguments = getArguments();
        String pupilId = (String) arguments.get(getResources().getString(R.string.pupilId));

        pupilDetailsPresenter.requestGetPupilById(pupilId);
    }

    private void initViews() {
        createPupilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPupilFromUI();
                pupilDetailsPresenter.onActionButtonClick(pupil);
            }
        });

        groupsSpinnerAdapter = new ArrayAdapter<Group>(getContext(), R.layout.spinner_item, groups);

        groupsSpinner.setAdapter(groupsSpinnerAdapter);
    }

    private void findViews(View view) {
        pupilFirstNameEditText = view.findViewById(R.id.pupilFirstNameEditText);
        pupilLastNameEditText = view.findViewById(R.id.pupilLastNameEditText);
        groupsSpinner = view.findViewById(R.id.pupilGroupsSpinner);

        createPupilButton = view.findViewById(R.id.buttonCreatePupil);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        pupilDetailsPresenter.destroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((SchoolApplication) getActivity().getApplication()).releasePupilDetailsComponent();
    }

    @Override
    public void onCreatePupilSuccess() {
        getActivity().finish();
    }

    @Override
    public void onUpdatePupilSuccess() {
        getActivity().finish();
    }

    @Override
    public void onRequestFail(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPupilByIdSuccess(PupilResponse pupilResponse) {
        setPupil(pupilResponse);
        fillEditTextWithPupilData(pupilResponse);
        changeButtonTextToUpdate();
    }

    private void setPupil(PupilResponse pupilResponse) {
        pupil.setId(pupilResponse.getId());
        pupil.setFirstName(pupilResponse.getFirstName());
        pupil.setLastName(pupilResponse.getLastName());
        pupil.setGroupId(pupilResponse.getGroup().getId());
    }

    private void setPupilFromUI(){
        pupil.setFirstName(pupilFirstNameEditText.getText().toString());
        pupil.setLastName(pupilLastNameEditText.getText().toString());

        Group selectedGroup = groups.get(groupsSpinner.getSelectedItemPosition());

        pupil.setGroupId(selectedGroup.getId());
    }

    private void changeButtonTextToUpdate() {
        createPupilButton.setText(getResources().getString(R.string.update));
    }

    private void fillEditTextWithPupilData(PupilResponse pupilResponse) {
        pupilFirstNameEditText.setText(pupilResponse.getFirstName());
        pupilLastNameEditText.setText(pupilResponse.getLastName());

        int groupsSpinnerPosition = getGroupsSpinnerPosition(pupilResponse);
        groupsSpinner.setSelection(groupsSpinnerPosition);
    }

    private int getGroupsSpinnerPosition(PupilResponse pupilResponse){
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getId().equalsIgnoreCase(pupilResponse.getGroup().getId())){
                return i;
            }
        }

        return 0;
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
    public void onGroupsFetchSuccess(GroupsResponse groupsResponse) {
        groups.clear();
        groups.addAll(groupsResponse.getGroups());
        groupsSpinnerAdapter.notifyDataSetChanged();
    }
}
