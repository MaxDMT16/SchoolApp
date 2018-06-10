package com.dmt.max.schoolschedule.lessons.interactors.details;

import com.dmt.max.schoolschedule.model.group.responses.GroupsResponse;
import com.dmt.max.schoolschedule.model.lesson.requests.CreateLessonRequest;
import com.dmt.max.schoolschedule.model.lesson.requests.UpdateLessonRequest;
import com.dmt.max.schoolschedule.model.lesson.responses.LessonResponse;
import com.dmt.max.schoolschedule.model.teachers.responses.TeachersResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Max on 03.06.2018.
 */

public interface LessonDetailsInteractor {
    Observable<ResponseBody> createLesson(String accessToken, CreateLessonRequest createLessonRequest);
    Observable<ResponseBody> updateLesson(String accessToken, UpdateLessonRequest updateLessonRequest);
    Observable<LessonResponse> getLessonById(String accessToken, String lessonId);

    Observable<GroupsResponse> getGroups(String accessToken);
    Observable<TeachersResponse> getTeachers(String accessToken);
}
