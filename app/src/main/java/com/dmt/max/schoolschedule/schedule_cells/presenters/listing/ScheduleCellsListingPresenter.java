package com.dmt.max.schoolschedule.schedule_cells.presenters.listing;

import com.dmt.max.schoolschedule.schedule_cells.views.listing.ScheduleCellsListingView;

/**
 * Created by Max on 09.06.2018.
 */

public interface ScheduleCellsListingPresenter {
    void setAccessToken(String accessToken);
    void setRefreshToken(String refreshToken);
    void deleteScheduleCell(String scheduleCellId);
    void setView(ScheduleCellsListingView view);
    void destroy();
}
