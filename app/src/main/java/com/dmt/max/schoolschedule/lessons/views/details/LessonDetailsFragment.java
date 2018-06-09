package com.dmt.max.schoolschedule.lessons.views.details;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.SchoolApplication;
import com.dmt.max.schoolschedule.lessons.presenters.details.LessonDetailsPresenter;
import com.dmt.max.schoolschedule.model.lesson.Lesson;
import com.dmt.max.schoolschedule.model.lesson.responses.LessonResponse;

import javax.inject.Inject;

public class LessonDetailsFragment extends Fragment implements LessonDetailsView {
    @Inject
    LessonDetailsPresenter lessonDetailsPresenter;

    TextInputEditText lessonTeacherIdEditText;
    TextInputEditText lessonGrouIdEditText;
    Spinner lessonSubject;
    Button actionButton;

    private Lesson lesson;

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

        initListeners();

        return rootView;
    }

    private void findViews(View view) {
        lessonTeacherIdEditText = view.findViewById(R.id.lessonTeacherIdEditText);
        lessonGrouIdEditText = view.findViewById(R.id.lessonGroupIdEditText);
        lessonSubject = view.findViewById(R.id.lessonSubjectSpinner);
        actionButton = view.findViewById(R.id.lessonActionButton);
    }

    private void initListeners() {
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLessonFromUI();
                lessonDetailsPresenter.onActionButtonClick(lesson);
            }
        });
    }

    private void setLessonFromUI() {
        lesson.setSubject((String)lessonSubject.getSelectedItem());
        lesson.setTeacherId(lessonTeacherIdEditText.getText().toString());
        lesson.setGroupId(lessonGrouIdEditText.getText().toString());
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
        lessonTeacherIdEditText.setText(lessonResponse.getTeacherId());
        lessonGrouIdEditText.setText(lessonResponse.getGroupId());
//        lessonSubject.setText(lessonResponse.getSubject());
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
