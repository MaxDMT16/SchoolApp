package com.dmt.max.schoolschedule.lessons.views.details;

import com.dmt.max.schoolschedule.model.lesson.responses.LessonResponse;

/**
 * Created by Max on 03.06.2018.
 */

public interface LessonDetailsView {
    void onCreateLessonSuccess();
    void onUpdateLessonSuccess();
    void onRequestFail(String message);
    void onRequestLessonByIdSuccess(LessonResponse lessonResponse);
    String getAccessToken();
    String getRefreshToken();
}
