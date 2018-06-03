package com.dmt.max.schoolschedule.lessons.presenters.listing;

import com.dmt.max.schoolschedule.lessons.views.listing.LessonsListingView;

/**
 * Created by Max on 03.06.2018.
 */

public interface LessonsListingPresenter {
    void setAccessToken(String accessToken);
    void setRefreshToken(String refreshToken);
    void deleteLesson(String lessonId);
    void setView(LessonsListingView view);
    void destroy();
}
