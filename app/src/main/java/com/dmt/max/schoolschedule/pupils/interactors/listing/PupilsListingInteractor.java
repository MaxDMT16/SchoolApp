package com.dmt.max.schoolschedule.pupils.interactors.listing;

import com.dmt.max.schoolschedule.model.pupil.resoponses.PupilsResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Max on 13.05.2018.
 */

public interface PupilsListingInteractor {
    Observable<PupilsResponse> getPupils(String accessToken);
    Observable<ResponseBody> deletePupil(String accessToken, String pupilId);
}
