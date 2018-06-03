package com.dmt.max.schoolschedule.lessons.views.listing;

import com.dmt.max.schoolschedule.model.lesson.Lesson;

import java.util.List;

/**
 * Created by Max on 03.06.2018.
 */

public interface LessonsListingView {
    void loadingStarted();
    void showLessons(List<Lesson> lessons);
    void loadingFailed(String errorMessage);
    void deleteLesson(String lessonId);
    void onDeleteSuccess();
    void onDeleteFailed(String message);
    void onLessonClick(String lessonId);
}
