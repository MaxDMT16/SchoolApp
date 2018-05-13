package com.dmt.max.schoolschedule.pupils.presenters;

import com.dmt.max.schoolschedule.pupils.views.PupilsListingView;

/**
 * Created by Max on 13.05.2018.
 */

public interface PupilsListingPresenter {
    void setView(PupilsListingView view, String accessToken);
    void destroy();
}
