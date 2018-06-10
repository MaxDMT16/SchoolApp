package com.dmt.max.schoolschedule.schedule_cells.views.details;

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
import android.widget.Spinner;
import android.widget.Toast;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.SchoolApplication;
import com.dmt.max.schoolschedule.model.schedule_cell.ScheduleCell;
import com.dmt.max.schoolschedule.model.schedule_cell.responses.ScheduleCellResponse;
import com.dmt.max.schoolschedule.schedule_cells.presenters.details.ScheduleCellDetailsPresenter;

import javax.inject.Inject;

public class ScheduleCellDetailsFragment extends Fragment implements ScheduleCellDetailsView {
    @Inject
    ScheduleCellDetailsPresenter scheduleCellDetailsPresenter;

    private TextInputEditText lessonIdEditText;
    private TextInputEditText lessonNumberEditText;
    private TextInputEditText roomEditText;
    private Spinner dayOfTheWeekSpinner;

    private Button actionButton;

    private ScheduleCell scheduleCell;

    public ScheduleCellDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((SchoolApplication) getActivity().getApplication()).createScheduleCellDetailsComponent().inject(this);

        scheduleCell = new ScheduleCell();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_schedule_cell_details, container, false);

        findViews(rootView);

        initListeners();

        return rootView;
    }

    private void findViews(View view) {
        lessonIdEditText = view.findViewById(R.id.cellLessonIdEditText);
        lessonNumberEditText = view.findViewById(R.id.cellLessonNumberEditText);
        roomEditText = view.findViewById(R.id.cellRoomEditText);
        dayOfTheWeekSpinner = view.findViewById(R.id.cellDayOfTheWeekSpinner);
        actionButton = view.findViewById(R.id.cellActionButton);
    }

    private void initListeners() {
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setScheduleCellFromUI();
                scheduleCellDetailsPresenter.onActionButtonClick(scheduleCell);
            }
        });
    }

    private void setScheduleCellFromUI() {
        scheduleCell.setLessonNumber(Integer.valueOf(lessonNumberEditText.getText().toString()));
        scheduleCell.setLessonId(lessonIdEditText.getText().toString());
        scheduleCell.setDay((String) dayOfTheWeekSpinner.getSelectedItem());
        scheduleCell.setRoom(Integer.valueOf(roomEditText.getText().toString()));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        scheduleCellDetailsPresenter.setView(this);

        Bundle arguments = getArguments();
        String scheduleCellId = (String) arguments.get(getResources().getString(R.string.scheduleCellId));

        scheduleCellDetailsPresenter.requestGetScheduleCellById(scheduleCellId);
    }

    @Override
    public void onCreateScheduleCellSuccess() {
        getActivity().finish();
    }

    @Override
    public void onUpdateScheduleCellSuccess() {
        getActivity().finish();
    }

    @Override
    public void onRequestFail(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestScheduleCellByIdSuccess(ScheduleCellResponse scheduleCellResponse) {
        setScheduleCell(scheduleCellResponse);
        fillScheduleCellData(scheduleCellResponse);
        changeButtonText();
    }

    private void fillScheduleCellData(ScheduleCellResponse scheduleCellResponse) {
        lessonNumberEditText.setText(scheduleCellResponse.getLessonNumber().toString());
        lessonIdEditText.setText(scheduleCellResponse.getLessonId());
        roomEditText.setText(scheduleCellResponse.getRoom().toString());
        dayOfTheWeekSpinner.setSelection(1); //// TODO: refactor
    }

    private void changeButtonText() {
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

    public void setScheduleCell(ScheduleCellResponse scheduleCellResponse) {
        scheduleCell.setId(scheduleCellResponse.getId());
        scheduleCell.setRoom(scheduleCellResponse.getRoom());
        scheduleCell.setDay(scheduleCellResponse.getDay());
        scheduleCell.setLessonId(scheduleCellResponse.getLessonId());
        scheduleCell.setLessonNumber(scheduleCellResponse.getLessonNumber());
    }
}
