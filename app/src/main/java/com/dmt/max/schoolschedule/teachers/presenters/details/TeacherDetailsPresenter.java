package com.dmt.max.schoolschedule.teachers.presenters.details;

import com.dmt.max.schoolschedule.model.teachers.Teacher;
import com.dmt.max.schoolschedule.model.teachers.requests.CreateTeacherRequest;
import com.dmt.max.schoolschedule.model.teachers.requests.UpdateTeacherRequest;
import com.dmt.max.schoolschedule.teachers.views.details.TeacherDetailsView;

/**
 * Created by Max on 02.06.2018.
 */

public interface TeacherDetailsPresenter {
    void setAccessToken(String accessToken);
    void setRefreshToken(String refreshToken);
    void createTeacher(CreateTeacherRequest createTeacherRequest);
    void updateTeacher(UpdateTeacherRequest updateTeacherRequest);
    void requestGetTeacherById(String teacherId);
    void onActionButtonClick(Teacher teacher);
    void setView(TeacherDetailsView view);
    void destroy();
}
