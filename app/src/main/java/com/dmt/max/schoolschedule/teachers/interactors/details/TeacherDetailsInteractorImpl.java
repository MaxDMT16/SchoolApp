package com.dmt.max.schoolschedule.teachers.interactors.details;

import com.dmt.max.schoolschedule.model.teachers.requests.CreateTeacherRequest;
import com.dmt.max.schoolschedule.model.teachers.requests.UpdateTeacherRequest;
import com.dmt.max.schoolschedule.model.teachers.responses.TeacherResponse;
import com.dmt.max.schoolschedule.network.SchoolSystemWebService;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Max on 02.06.2018.
 */

public class TeacherDetailsInteractorImpl implements TeacherDetailsInteractor {
    private SchoolSystemWebService schoolSystemWebService;

    public TeacherDetailsInteractorImpl(SchoolSystemWebService schoolSystemWebService) {
        this.schoolSystemWebService = schoolSystemWebService;
    }

    @Override
    public Observable<ResponseBody> createTeacher(String accessToken, CreateTeacherRequest createTeacherRequest) {
        return null;
    }

    @Override
    public Observable<ResponseBody> updateTeacher(String accessToken, UpdateTeacherRequest updateTeacherRequest) {
        return null;
    }

    @Override
    public Observable<TeacherResponse> getTeacherById(String accessToken, String teacherId) {
        return null;
    }
}
