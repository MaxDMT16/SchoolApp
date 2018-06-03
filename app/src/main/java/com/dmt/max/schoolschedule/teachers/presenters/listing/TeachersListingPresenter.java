package com.dmt.max.schoolschedule.teachers.presenters.listing;

import com.dmt.max.schoolschedule.teachers.views.listing.TeachersListingView;

/**
 * Created by Max on 02.06.2018.
 */

public interface TeachersListingPresenter {
    void setAccessToken(String accessToken);
    void setRefreshToken(String refreshToken);
    void deleteTeacher(String teacherId);
    void setView(TeachersListingView view);
    void destroy();
}
