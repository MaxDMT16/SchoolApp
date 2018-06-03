package com.dmt.max.schoolschedule.teachers.views.details;

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
import com.dmt.max.schoolschedule.model.group.responses.GroupResponse;
import com.dmt.max.schoolschedule.model.teachers.Teacher;
import com.dmt.max.schoolschedule.model.teachers.responses.TeacherResponse;
import com.dmt.max.schoolschedule.teachers.presenters.details.TeacherDetailsPresenter;

import javax.inject.Inject;

public class TeacherDetailsFragment extends Fragment implements TeacherDetailsView{
    @Inject
    TeacherDetailsPresenter teacherDetailsPresenter;

    TextInputEditText teacherFirstName;
    TextInputEditText teacherLastName;
    Button actionButton;

    private Teacher teacher;

    public TeacherDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((SchoolApplication) getActivity().getApplication()).createTeacherDetailsComponent().inject(this);

        teacher = new Teacher();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_teacher_details, container, false);

        findViews(rootView);

        initListeners();

        return rootView;
    }

    private void initListeners() {
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTeacherFromUI();
                teacherDetailsPresenter.onActionButtonClick(teacher);
            }
        });
    }

    private void setTeacherFromUI() {
        teacher.setFirstName(teacherFirstName.getText().toString());
        teacher.setLastName(teacherLastName.getText().toString());
    }

    private void findViews(View view) {
        teacherFirstName = view.findViewById(R.id.teacherFirstNameEditText);
        teacherLastName = view.findViewById(R.id.teacherLastNameEditText);
        actionButton = view.findViewById(R.id.teacherActionButton);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        teacherDetailsPresenter.setView(this);

        Bundle arguments = getArguments();
        String teacherId = (String) arguments.get(getResources().getString(R.string.teacherId));

        teacherDetailsPresenter.requestGetTeacherById(teacherId);
    }

    @Override
    public void onCreateTeacherSuccess() {
        getActivity().finish();
    }

    @Override
    public void onUpdateTeacherSuccess() {
        getActivity().finish();
    }

    @Override
    public void onRequestFail(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestTeacherByIdSuccess(TeacherResponse teacherResponse) {
        setTeacher(teacherResponse);
        fillEditTextWithTeacherData(teacherResponse);
        changeButtonTextToUpdate();
    }

    private void changeButtonTextToUpdate() {
        actionButton.setText(getResources().getString(R.string.update));
    }

    private void fillEditTextWithTeacherData(TeacherResponse teacherResponse) {
        teacherFirstName.setText(teacherResponse.getFirstName());
        teacherLastName.setText(teacherResponse.getLastName());
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

    public void setTeacher(TeacherResponse teacherResponse) {
        teacher.setId(teacherResponse.getId());
        teacher.setFirstName(teacherResponse.getFirstName());
        teacher.setLastName(teacherResponse.getLastName());
    }
}
