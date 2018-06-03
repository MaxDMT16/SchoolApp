package com.dmt.max.schoolschedule.teachers.interactors.details;

import com.dmt.max.schoolschedule.model.teachers.requests.CreateTeacherRequest;
import com.dmt.max.schoolschedule.model.teachers.requests.UpdateTeacherRequest;
import com.dmt.max.schoolschedule.model.teachers.responses.TeacherResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Max on 02.06.2018.
 */

public interface TeacherDetailsInteractor {
    Observable<ResponseBody> createTeacher(String accessToken, CreateTeacherRequest createTeacherRequest);
    Observable<ResponseBody> updateTeacher(String accessToken, UpdateTeacherRequest updateTeacherRequest);
    Observable<TeacherResponse> getTeacherById(String accessToken, String teacherId);
}
