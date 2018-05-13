package com.dmt.max.schoolschedule.pupils.interactors;

import com.dmt.max.schoolschedule.model.pupil.Pupil;
import com.dmt.max.schoolschedule.model.pupil.PupilsResponse;
import com.dmt.max.schoolschedule.network.SchoolSystemWebService;

import java.util.List;

import io.reactivex.Observable;

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
}
