package com.dmt.max.schoolschedule.model.teachers.responses;

import com.dmt.max.schoolschedule.model.teachers.Teacher;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Max on 31.05.2018.
 */
public class TeachersResponse {

    @SerializedName("teachers")
    @Expose
    private List<Teacher> teachers = null;

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

}