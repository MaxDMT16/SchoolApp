package com.dmt.max.schoolschedule.teachers.presenters.listing;

import com.dmt.max.schoolschedule.teachers.interactors.listing.TeachersListingInteractor;
import com.dmt.max.schoolschedule.teachers.views.listing.TeachersListingView;

/**
 * Created by Max on 02.06.2018.
 */

public class TeachersListingPresenterImpl implements TeachersListingPresenter {
    private TeachersListingInteractor teachersListingInteractor;

    public TeachersListingPresenterImpl(TeachersListingInteractor teachersListingInteractor) {
        this.teachersListingInteractor = teachersListingInteractor;
    }

    @Override
    public void setAccessToken(String accessToken) {

    }

    @Override
    public void setRefreshToken(String refreshToken) {

    }

    @Override
    public void deletePupil(String teacherId) {

    }

    @Override
    public void setView(TeachersListingView view) {

    }

    @Override
    public void destroy() {

    }
}
