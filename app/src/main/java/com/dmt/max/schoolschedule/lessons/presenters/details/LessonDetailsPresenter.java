package com.dmt.max.schoolschedule.lessons.presenters.details;

import com.dmt.max.schoolschedule.lessons.views.details.LessonDetailsView;
import com.dmt.max.schoolschedule.model.lesson.Lesson;
import com.dmt.max.schoolschedule.model.lesson.requests.CreateLessonRequest;
import com.dmt.max.schoolschedule.model.lesson.requests.UpdateLessonRequest;

/**
 * Created by Max on 03.06.2018.
 */

public interface LessonDetailsPresenter {
    void setAccessToken(String accessToken);

    void setRefreshToken(String refreshToken);

    void createLesson(CreateLessonRequest createLessonRequest);

    void updateLesson(UpdateLessonRequest updateLessonRequest);

    void requestGetLessonById(String lessonId);

    void onActionButtonClick(Lesson lesson);

    void setView(LessonDetailsView view);

    void destroy();
}
