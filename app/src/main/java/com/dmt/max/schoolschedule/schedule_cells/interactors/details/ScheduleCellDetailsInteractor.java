package com.dmt.max.schoolschedule.schedule_cells.interactors.details;

import com.dmt.max.schoolschedule.model.schedule_cell.requests.CreateScheduleCellRequest;
import com.dmt.max.schoolschedule.model.schedule_cell.requests.UpdateScheduleCellRequest;
import com.dmt.max.schoolschedule.model.schedule_cell.responses.ScheduleCellResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Max on 09.06.2018.
 */

public interface ScheduleCellDetailsInteractor {
    Observable<ResponseBody> createScheduleCell(String accessToken, CreateScheduleCellRequest createScheduleCellRequest);
    Observable<ResponseBody> updateScheduleCell(String accessToken, UpdateScheduleCellRequest updateScheduleCellRequest);
    Observable<ScheduleCellResponse> getScheduleCellById(String accessToken, String scheduleCellId);
}
