package com.dmt.max.schoolschedule.model.lesson.responses;

import com.dmt.max.schoolschedule.model.lesson.Lesson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Max on 03.06.2018.
 */

public class LessonsResponse {

    @SerializedName("lessons")
    @Expose
    private List<Lesson> lessons = null;

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

}