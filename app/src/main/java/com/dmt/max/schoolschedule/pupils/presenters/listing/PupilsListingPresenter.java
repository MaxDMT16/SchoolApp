package com.dmt.max.schoolschedule.pupils.presenters.listing;

import com.dmt.max.schoolschedule.pupils.views.listing.PupilsListingView;

/**
 * Created by Max on 13.05.2018.
 */

public interface PupilsListingPresenter {
    void setAccessToken(String accessToken);
    void setRefreshToken(String refreshToken);
    void deletePupil(String pupilId);
    void setView(PupilsListingView view);
    void destroy();
}
