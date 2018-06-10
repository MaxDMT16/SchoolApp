package com.dmt.max.schoolschedule.schedule_cells.interactors.listing;

import com.dmt.max.schoolschedule.model.schedule_cell.responses.ScheduleCellsResponse;
import com.dmt.max.schoolschedule.network.SchoolSystemWebService;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Max on 09.06.2018.
 */

public class ScheduleCellsListingInteractorImpl implements ScheduleCellsListingInteractor {
    private SchoolSystemWebService schoolSystemWebService;

    public ScheduleCellsListingInteractorImpl(SchoolSystemWebService schoolSystemWebService) {
        this.schoolSystemWebService = schoolSystemWebService;
    }

    @Override
    public Observable<ResponseBody> deleteScheduleCell(String accessToken, String scheduleCellId) {
        return schoolSystemWebService.deleteScheduleCell(accessToken, scheduleCellId);
    }

    @Override
    public Observable<ScheduleCellsResponse> getSchedulCells(String accessToken) {
        return schoolSystemWebService.getScheduleCells(accessToken);
    }
}
