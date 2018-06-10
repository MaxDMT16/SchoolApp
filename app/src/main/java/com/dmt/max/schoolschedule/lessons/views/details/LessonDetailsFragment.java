package com.dmt.max.schoolschedule.lessons.views.details;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.dmt.max.schoolschedule.lessons.presenters.details.LessonDetailsPresenter;
import com.dmt.max.schoolschedule.model.group.Group;
import com.dmt.max.schoolschedule.model.group.responses.GroupsResponse;
import com.dmt.max.schoolschedule.model.lesson.Lesson;
import com.dmt.max.schoolschedule.model.lesson.responses.LessonResponse;
import com.dmt.max.schoolschedule.model.teachers.Teacher;
import com.dmt.max.schoolschedule.model.teachers.responses.TeachersResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class LessonDetailsFragment extends Fragment implements LessonDetailsView {
    @Inject
    LessonDetailsPresenter lessonDetailsPresenter;

    Spinner lessonTeacherSpinner;
    Spinner lessonGroupSpinner;
    Spinner lessonSubject;
    Button actionButton;

    private Lesson lesson;
    private List<Group> groups = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();

    private ArrayAdapter<Group> groupsSpinnerAdapter;
    private ArrayAdapter<Teacher> teachersSpinnerAdapter;

    public LessonDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((SchoolApplication) getActivity().getApplication()).createLessonDetailsComponent().inject(this);

        lesson = new Lesson();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_lesson_details, container, false);

        findViews(rootView);

        initViews();

        return rootView;
    }

    private void findViews(View view) {
        lessonTeacherSpinner = view.findViewById(R.id.lessonTeacherSpinner);
        lessonGroupSpinner = view.findViewById(R.id.lessonGroupSpinner);
        lessonSubject = view.findViewById(R.id.lessonSubjectSpinner);
        actionButton = view.findViewById(R.id.lessonActionButton);
    }

    private void initViews() {
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLessonFromUI();
                lessonDetailsPresenter.onActionButtonClick(lesson);
            }
        });

        groupsSpinnerAdapter = new ArrayAdapter<Group>(getContext(), R.layout.spinner_item, groups);
        lessonGroupSpinner.setAdapter(groupsSpinnerAdapter);

        teachersSpinnerAdapter = new ArrayAdapter<Teacher>(getContext(), R.layout.spinner_item, teachers);
        lessonTeacherSpinner.setAdapter(teachersSpinnerAdapter);
    }

    private void setLessonFromUI() {
        lesson.setSubject((String)lessonSubject.getSelectedItem());
        lesson.setTeacherId(teachers.get(lessonTeacherSpinner.getSelectedItemPosition()).getId());
        lesson.setGroupId(groups.get(lessonGroupSpinner.getSelectedItemPosition()).getId());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lessonDetailsPresenter.setView(this);

        Bundle arguments = getArguments();
        String lessonId = (String) arguments.get(getResources().getString(R.string.lessonId));

        lessonDetailsPresenter.requestGetLessonById(lessonId);
    }

    @Override
    public void onCreateLessonSuccess() {
        getActivity().finish();
    }

    @Override
    public void onUpdateLessonSuccess() {
        getActivity().finish();
    }

    @Override
    public void onRequestFail(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestLessonByIdSuccess(LessonResponse lessonResponse) {
        setLesson(lessonResponse);
        fillEditTextWithLessonData(lessonResponse);
        changeButtonTextToUpdate();
    }

    private void fillEditTextWithLessonData(LessonResponse lessonResponse) {
        lessonTeacherSpinner.setSelection(getTeacherPosition(lessonResponse),true);
        lessonGroupSpinner.setSelection(getGroupPosition(lessonResponse), true);
        lessonSubject.setSelection(getSubjectPosition(lessonResponse));
    }

    private int getTeacherPosition(LessonResponse lessonResponse) {
        for (int i = 0; i < teachers.size(); i++){
            if (teachers.get(i).getId().equalsIgnoreCase(lessonResponse.getTeacherId())){
                return i;
            }
        }

        return 0;
    }

    private int getGroupPosition(LessonResponse lessonResponse) {
        for (int i = 0; i < groups.size(); i++){
            if (groups.get(i).getId().equalsIgnoreCase(lessonResponse.getGroupId())){
                return i;
            }
        }
        
        return 0;
    }

    private int getSubjectPosition(LessonResponse lessonResponse) {
        String[] subjects = getResources().getStringArray(R.array.subjects);
        for (int i = 0; i < subjects.length; i++){
            if (subjects[i].equalsIgnoreCase(lessonResponse.getSubject())){
                return i;
            }
        }

        return 0;
    }

    private void changeButtonTextToUpdate() {
        actionButton.setText(getResources().getString(R.string.update));
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

    @Override
    public void onTeachersFetchSuccess(TeachersResponse teachersResponse) {
        teachers.clear();
        teachers.addAll(teachersResponse.getTeachers());
        teachersSpinnerAdapter.notifyDataSetChanged();
    }

    public void setLesson(LessonResponse lessonResponse) {
        lesson.setId(lessonResponse.getId());
        lesson.setGroupId(lessonResponse.getGroupId());
        lesson.setTeacherId(lessonResponse.getTeacherId());
        lesson.setSubject(lessonResponse.getSubject());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((SchoolApplication) getActivity().getApplication()).releaseLessonDetailsComponent();
    }
}
