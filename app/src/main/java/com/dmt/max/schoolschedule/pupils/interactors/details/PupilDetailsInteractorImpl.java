package com.dmt.max.schoolschedule.pupils.interactors.details;

import com.dmt.max.schoolschedule.model.group.responses.GroupsResponse;
import com.dmt.max.schoolschedule.model.pupil.Pupil;
import com.dmt.max.schoolschedule.model.pupil.requests.CreatePupilRequest;
import com.dmt.max.schoolschedule.model.pupil.requests.PupilByIdRequest;
import com.dmt.max.schoolschedule.model.pupil.requests.UpdatePupilRequest;
import com.dmt.max.schoolschedule.model.pupil.resoponses.PupilResponse;
import com.dmt.max.schoolschedule.network.SchoolSystemWebService;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Max on 19.05.2018.
 */

public class PupilDetailsInteractorImpl implements PupilDetailsInteractor {
    private SchoolSystemWebService schoolSystemWebService;

    public PupilDetailsInteractorImpl(SchoolSystemWebService schoolSystemWebService) {
        this.schoolSystemWebService = schoolSystemWebService;
    }

    @Override
    public Observable<ResponseBody> createPupil(String accessToken, CreatePupilRequest createPupilRequest) {
        return schoolSystemWebService.createPupil(accessToken, createPupilRequest);
    }

    @Override
    public Observable<PupilResponse> getPupilById(String accessToken, PupilByIdRequest pupilByIdRequest) {
        return schoolSystemWebService.getPupilById(accessToken, pupilByIdRequest.getPupilId());
    }

    @Override
    public Observable<ResponseBody> updatePupil(String accessToken, UpdatePupilRequest updatePupilRequest) {
        return schoolSystemWebService.updatePupil(accessToken, updatePupilRequest);
    }

    @Override
    public Observable<GroupsResponse> getGroups(String accessToken) {
        return schoolSystemWebService.getGroups(accessToken);
    }
}
