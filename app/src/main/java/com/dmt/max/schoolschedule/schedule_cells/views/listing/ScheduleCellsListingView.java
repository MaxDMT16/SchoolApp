package com.dmt.max.schoolschedule.schedule_cells.views.listing;

import com.dmt.max.schoolschedule.model.schedule_cell.ScheduleCell;

import java.util.List;

/**
 * Created by Max on 09.06.2018.
 */

public interface ScheduleCellsListingView {
    void loadingStarted();

    void showScheduleCells(List<ScheduleCell> scheduleCells);

    void loadingFailed(String errorMessage);

    void deleteScheduleCell(String scheduleCellId);

    void onDeleteSuccess();

    void onDeleteFailed(String message);

    void onScheduleCellClick(String scheduleCellId);
}
