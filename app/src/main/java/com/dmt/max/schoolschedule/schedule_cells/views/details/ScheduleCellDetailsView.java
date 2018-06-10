package com.dmt.max.schoolschedule.schedule_cells.views.details;

import com.dmt.max.schoolschedule.model.schedule_cell.responses.ScheduleCellResponse;

/**
 * Created by Max on 09.06.2018.
 */

public interface ScheduleCellDetailsView {
    void onCreateScheduleCellSuccess();
    void onUpdateScheduleCellSuccess();
    void onRequestFail(String message);
    void onRequestScheduleCellByIdSuccess(ScheduleCellResponse scheduleCellResponse);
    String getAccessToken();
    String getRefreshToken();
}
