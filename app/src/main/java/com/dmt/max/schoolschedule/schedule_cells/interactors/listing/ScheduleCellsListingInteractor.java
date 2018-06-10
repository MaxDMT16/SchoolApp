package com.dmt.max.schoolschedule.schedule_cells.interactors.listing;

import com.dmt.max.schoolschedule.model.schedule_cell.responses.ScheduleCellsResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Max on 09.06.2018.
 */

public interface ScheduleCellsListingInteractor {
    Observable<ResponseBody> deleteScheduleCell(String accessToken, String scheduleCellId);
    Observable<ScheduleCellsResponse> getSchedulCells(String accessToken);
}
