package com.dmt.max.schoolschedule.model.schedule_cell.requests;

/**
 * Created by Max on 09.06.2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateScheduleCellRequest {

    @SerializedName("lessonId")
    @Expose
    private String lessonId;
    @SerializedName("lessonNumber")
    @Expose
    private Integer lessonNumber;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("room")
    @Expose
    private Integer room;

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(Integer lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

}