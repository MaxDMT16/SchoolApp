package com.dmt.max.schoolschedule.lessons.interactors.details;

import com.dmt.max.schoolschedule.model.group.responses.GroupsResponse;
import com.dmt.max.schoolschedule.model.lesson.requests.CreateLessonRequest;
import com.dmt.max.schoolschedule.model.lesson.requests.UpdateLessonRequest;
import com.dmt.max.schoolschedule.model.lesson.responses.LessonResponse;
import com.dmt.max.schoolschedule.model.teachers.responses.TeachersResponse;
import com.dmt.max.schoolschedule.network.SchoolSystemWebService;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Max on 03.06.2018.
 */

public class LessonDetailsInteractorImpl implements LessonDetailsInteractor {
    SchoolSystemWebService schoolSystemWebService;

    public LessonDetailsInteractorImpl(SchoolSystemWebService schoolSystemWebService) {
        this.schoolSystemWebService = schoolSystemWebService;
    }

    @Override
    public Observable<ResponseBody> createLesson(String accessToken, CreateLessonRequest createLessonRequest) {
        return schoolSystemWebService.createLesson(accessToken, createLessonRequest);
    }

    @Override
    public Observable<ResponseBody> updateLesson(String accessToken, UpdateLessonRequest updateLessonRequest) {
        return schoolSystemWebService.updateLesson(accessToken, updateLessonRequest);
    }

    @Override
    public Observable<LessonResponse> getLessonById(String accessToken, String lessonId) {
        return schoolSystemWebService.getLessonById(accessToken, lessonId);
    }

    @Override
    public Observable<GroupsResponse> getGroups(String accessToken) {
        return schoolSystemWebService.getGroups(accessToken);
    }

    @Override
    public Observable<TeachersResponse> getTeachers(String accessToken) {
        return schoolSystemWebService.getTeachers(accessToken);
    }
}
