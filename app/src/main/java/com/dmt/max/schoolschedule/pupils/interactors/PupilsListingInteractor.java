package com.dmt.max.schoolschedule.pupils.interactors;

import com.dmt.max.schoolschedule.model.pupil.PupilsResponse;

import io.reactivex.Observable;

/**
 * Created by Max on 13.05.2018.
 */

public interface PupilsListingInteractor {
    Observable<PupilsResponse> getPupils(String accessToken);
}
