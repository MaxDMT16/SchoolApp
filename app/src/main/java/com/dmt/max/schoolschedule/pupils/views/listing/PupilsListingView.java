package com.dmt.max.schoolschedule.pupils.views.listing;

import com.dmt.max.schoolschedule.model.pupil.Pupil;

import java.util.List;

/**
 * Created by Max on 13.05.2018.
 */

public interface PupilsListingView {
    void loadingStarted();

    void showPupils(List<Pupil> pupils);

    void loadingFailed(String errorMessage);

    void deletePupil(String pupilId);

    void onDeleteSuccess();

    void onDeleteFailed(String message);

    void onPupilClick(String pupilId);
}

