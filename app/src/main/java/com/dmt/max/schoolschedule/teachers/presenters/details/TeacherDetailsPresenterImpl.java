package com.dmt.max.schoolschedule.teachers.presenters.details;

import com.dmt.max.schoolschedule.model.teachers.Teacher;
import com.dmt.max.schoolschedule.model.teachers.requests.CreateTeacherRequest;
import com.dmt.max.schoolschedule.model.teachers.requests.UpdateTeacherRequest;
import com.dmt.max.schoolschedule.teachers.interactors.details.TeacherDetailsInteractor;
import com.dmt.max.schoolschedule.teachers.views.details.TeacherDetailsView;

/**
 * Created by Max on 02.06.2018.
 */

public class TeacherDetailsPresenterImpl implements TeacherDetailsPresenter {
    TeacherDetailsInteractor teacherDetailsInteractor;

    public TeacherDetailsPresenterImpl(TeacherDetailsInteractor teacherDetailsInteractor) {
        this.teacherDetailsInteractor = teacherDetailsInteractor;
    }

    @Override
    public void setAccessToken(String accessToken) {

    }

    @Override
    public void setRefreshToken(String refreshToken) {

    }

    @Override
    public void createTeacher(CreateTeacherRequest createTeacherRequest) {

    }

    @Override
    public void updateTeacher(UpdateTeacherRequest updateTeacherRequest) {

    }

    @Override
    public void requestGetTeacherById(String teacherId) {

    }

    @Override
    public void onActionButtonClick(Teacher teacher) {

    }

    @Override
    public void setView(TeacherDetailsView view) {

    }

    @Override
    public void destroy() {

    }
}
