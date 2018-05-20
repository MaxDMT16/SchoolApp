package com.dmt.max.schoolschedule.pupils.interactors.listing;

import com.dmt.max.schoolschedule.model.pupil.resoponses.PupilsResponse;
import com.dmt.max.schoolschedule.network.SchoolSystemWebService;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Max on 13.05.2018.
 */

public class PupilsListingInteractorImpl implements PupilsListingInteractor {

    private SchoolSystemWebService schoolSystemWebService;

    public PupilsListingInteractorImpl(SchoolSystemWebService schoolSystemWebService) {
        this.schoolSystemWebService = schoolSystemWebService;
    }

    @Override
    public Observable<PupilsResponse> getPupils(String accessToken) {
        return schoolSystemWebService.getPupils(accessToken);
    }

    @Override
    public Observable<ResponseBody> deletePupil(String accessToken, String pupilId) {
        return schoolSystemWebService.deletePupil(accessToken, pupilId);
    }
}
