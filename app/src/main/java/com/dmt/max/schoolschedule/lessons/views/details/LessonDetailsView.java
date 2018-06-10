package com.dmt.max.schoolschedule.lessons.views.details;

import com.dmt.max.schoolschedule.model.group.responses.GroupsResponse;
import com.dmt.max.schoolschedule.model.lesson.responses.LessonResponse;
import com.dmt.max.schoolschedule.model.teachers.responses.TeachersResponse;

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

    void onGroupsFetchSuccess(GroupsResponse groupsResponse);

    void onTeachersFetchSuccess(TeachersResponse teachersResponse);
}
