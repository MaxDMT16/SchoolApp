package com.dmt.max.schoolschedule.model.schedule_cell.responses;

/**
 * Created by Max on 09.06.2018.
 */

import java.util.List;

import com.dmt.max.schoolschedule.model.schedule_cell.ScheduleCell;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleCellsResponse {

    @SerializedName("scheduleCells")
    @Expose
    private List<ScheduleCell> scheduleCells = null;

    public List<ScheduleCell> getScheduleCells() {
        return scheduleCells;
    }

    public void setScheduleCells(List<ScheduleCell> scheduleCells) {
        this.scheduleCells = scheduleCells;
    }

}