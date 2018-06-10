package com.dmt.max.schoolschedule.schedule_cells.presenters.details;

import com.dmt.max.schoolschedule.model.schedule_cell.ScheduleCell;
import com.dmt.max.schoolschedule.model.schedule_cell.requests.CreateScheduleCellRequest;
import com.dmt.max.schoolschedule.model.schedule_cell.requests.UpdateScheduleCellRequest;
import com.dmt.max.schoolschedule.schedule_cells.views.details.ScheduleCellDetailsView;

/**
 * Created by Max on 09.06.2018.
 */

public interface ScheduleCellDetailsPresenter {
    void setAccessToken(String accessToken);

    void setRefreshToken(String refreshToken);

    void createScheduleCell(CreateScheduleCellRequest createScheduleCellRequest);

    void updateScheduleCell(UpdateScheduleCellRequest updateScheduleCellRequest);

    void requestGetScheduleCellById(String scheduleCellId);

    void onActionButtonClick(ScheduleCell scheduleCell);

    void setView(ScheduleCellDetailsView view);

    void destroy();
}
