package com.dmt.max.schoolschedule.model.schedule_cell.requests;

/**
 * Created by Max on 09.06.2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateScheduleCellRequest {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("lessonId")
    @Expose
    private String lessonId;
    @SerializedName("lessonNumber")
    @Expose
    private String lessonNumber;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("room")
    @Expose
    private Integer room;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(String lessonNumber) {
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