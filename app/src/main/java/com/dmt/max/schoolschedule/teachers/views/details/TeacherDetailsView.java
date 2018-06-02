package com.dmt.max.schoolschedule.teachers.views.details;

import com.dmt.max.schoolschedule.model.teachers.responses.TeacherResponse;

/**
 * Created by Max on 02.06.2018.
 */

public interface TeacherDetailsView {
    void onCreateTeacherSuccess();
    void onUpdateTeacherSuccess();
    void onRequestFail(String message);
    void onRequestTeacherByIdSuccess(TeacherResponse teacherResponse);
    String getAccessToken();
    String getRefreshToken();
}
