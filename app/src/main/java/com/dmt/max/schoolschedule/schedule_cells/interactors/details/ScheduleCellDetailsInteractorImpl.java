package com.dmt.max.schoolschedule.schedule_cells.interactors.details;

import com.dmt.max.schoolschedule.model.schedule_cell.requests.CreateScheduleCellRequest;
import com.dmt.max.schoolschedule.model.schedule_cell.requests.UpdateScheduleCellRequest;
import com.dmt.max.schoolschedule.model.schedule_cell.responses.ScheduleCellResponse;
import com.dmt.max.schoolschedule.network.SchoolSystemWebService;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Max on 09.06.2018.
 */

public class ScheduleCellDetailsInteractorImpl implements ScheduleCellDetailsInteractor {
    private SchoolSystemWebService schoolSystemWebService;

    public ScheduleCellDetailsInteractorImpl(SchoolSystemWebService schoolSystemWebService) {
        this.schoolSystemWebService = schoolSystemWebService;
    }

    @Override
    public Observable<ResponseBody> createScheduleCell(String accessToken, CreateScheduleCellRequest createScheduleCellRequest) {
        return schoolSystemWebService.createScheduleCell(accessToken, createScheduleCellRequest);
    }

    @Override
    public Observable<ResponseBody> updateScheduleCell(String accessToken, UpdateScheduleCellRequest updateScheduleCellRequest) {
        return schoolSystemWebService.updateScheduleCell(accessToken, updateScheduleCellRequest);
    }

    @Override
    public Observable<ScheduleCellResponse> getScheduleCellById(String accessToken, String scheduleCellId) {
        return schoolSystemWebService.getScheduleCellById(accessToken, scheduleCellId);
    }
}
