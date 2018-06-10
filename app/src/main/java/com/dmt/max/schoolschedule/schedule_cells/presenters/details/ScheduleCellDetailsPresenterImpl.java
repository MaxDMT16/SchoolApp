package com.dmt.max.schoolschedule.schedule_cells.presenters.details;

import com.dmt.max.schoolschedule.model.schedule_cell.ScheduleCell;
import com.dmt.max.schoolschedule.model.schedule_cell.requests.CreateScheduleCellRequest;
import com.dmt.max.schoolschedule.model.schedule_cell.requests.UpdateScheduleCellRequest;
import com.dmt.max.schoolschedule.model.schedule_cell.responses.ScheduleCellResponse;
import com.dmt.max.schoolschedule.schedule_cells.interactors.details.ScheduleCellDetailsInteractor;
import com.dmt.max.schoolschedule.schedule_cells.views.details.ScheduleCellDetailsView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Max on 09.06.2018.
 */

public class ScheduleCellDetailsPresenterImpl implements ScheduleCellDetailsPresenter {
    private ScheduleCellDetailsInteractor scheduleCellDetailsInteractor;
    private ScheduleCellDetailsView view;

    String accessToken;
    String refreshToken;

    private boolean isUpdateAction;

    public ScheduleCellDetailsPresenterImpl(ScheduleCellDetailsInteractor scheduleCellDetailsInteractor) {
        this.scheduleCellDetailsInteractor = scheduleCellDetailsInteractor;
    }


    @Override
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public void createScheduleCell(CreateScheduleCellRequest createScheduleCellRequest) {
        scheduleCellDetailsInteractor.createScheduleCell(accessToken, createScheduleCellRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onCreateScheduleCellSuccess, this::onCreateScheduleCellFail);
    }

    private void onCreateScheduleCellSuccess(ResponseBody responseBody) {
        if (isViewAttached()) {
            view.onCreateScheduleCellSuccess();
        }
    }

    private void onCreateScheduleCellFail(Throwable throwable) {
        if (isViewAttached()) {
            view.onRequestFail(throwable.getMessage());
        }
    }

    @Override
    public void updateScheduleCell(UpdateScheduleCellRequest updateScheduleCellRequest) {
        scheduleCellDetailsInteractor.updateScheduleCell(accessToken, updateScheduleCellRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onUpdateScheduleCellSuccess, this::onUpdateScheduleCellFail);
    }

    private void onUpdateScheduleCellSuccess(ResponseBody responseBody) {
        if (isViewAttached()) {
            view.onUpdateScheduleCellSuccess();
        }
    }

    private void onUpdateScheduleCellFail(Throwable throwable) {
        if (isViewAttached()) {
            view.onRequestFail(throwable.getMessage());
        }
    }

    @Override
    public void requestGetScheduleCellById(String scheduleCellId) {
        if (scheduleCellId == null){
            isUpdateAction = false;
            return;
        }

        scheduleCellDetailsInteractor.getScheduleCellById(accessToken, scheduleCellId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetScheduleCellByIdSuccess, this::onGetScheduleCellByIdFail);
    }

    private void onGetScheduleCellByIdSuccess(ScheduleCellResponse scheduleCellResponse) {
        if (isViewAttached()){
            view.onRequestScheduleCellByIdSuccess(scheduleCellResponse);
        }
    }

    private void onGetScheduleCellByIdFail(Throwable throwable) {
        if (isViewAttached()){
            view.onRequestFail(throwable.getMessage());
        }
    }

    @Override
    public void onActionButtonClick(ScheduleCell scheduleCell) {
        if (isUpdateAction){
            UpdateScheduleCellRequest updateScheduleCellRequest = getUpdateScheduleCellRequest(scheduleCell);
            updateScheduleCell(updateScheduleCellRequest);
        }
        else {
            CreateScheduleCellRequest createScheduleCellRequest = getCreateScheduleCellRequest(scheduleCell);
            createScheduleCell(createScheduleCellRequest);
        }
    }

    private UpdateScheduleCellRequest getUpdateScheduleCellRequest(ScheduleCell scheduleCell) {
        UpdateScheduleCellRequest request = new UpdateScheduleCellRequest();
        request.setId(scheduleCell.getId());
        request.setLessonId(scheduleCell.getLessonId());
        request.setLessonNumber(scheduleCell.getLessonNumber());
        request.setDay(scheduleCell.getDay());
        request.setRoom(scheduleCell.getRoom());

        return request;
    }

    private CreateScheduleCellRequest getCreateScheduleCellRequest(ScheduleCell scheduleCell) {
        CreateScheduleCellRequest request = new CreateScheduleCellRequest();
        request.setLessonId(scheduleCell.getLessonId());
        request.setLessonNumber(scheduleCell.getLessonNumber());
        request.setDay(scheduleCell.getDay());
        request.setRoom(scheduleCell.getRoom());

        return request;
    }

    @Override
    public void setView(ScheduleCellDetailsView view) {
        this.view = view;
        if (isViewAttached()){
            accessToken = view.getAccessToken();
            refreshToken = view.getRefreshToken();
        }
    }

    @Override
    public void destroy() {
        this.view = null;
    }

    private boolean isViewAttached() {
        return view != null;
    }
}
