package com.dmt.max.schoolschedule.model.lesson.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Max on 03.06.2018.
 */

public class CreateLessonRequest {
    @SerializedName("teacherId")
    @Expose
    private String teacherId;
    @SerializedName("groupId")
    @Expose
    private String groupId;
    @SerializedName("subject")
    @Expose
    private String subject;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
