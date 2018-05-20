package com.dmt.max.schoolschedule.pupils.interactors.details;

import com.dmt.max.schoolschedule.model.pupil.requests.CreatePupilRequest;
import com.dmt.max.schoolschedule.model.pupil.Pupil;
import com.dmt.max.schoolschedule.model.pupil.requests.PupilByIdRequest;
import com.dmt.max.schoolschedule.model.pupil.requests.UpdatePupilRequest;
import com.dmt.max.schoolschedule.model.pupil.resoponses.PupilResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Max on 19.05.2018.
 */

public interface PupilDetailsInteractor {
    Observable<ResponseBody> createPupil(String accessToken, CreatePupilRequest createPupilRequest);
    Observable<PupilResponse> getPupilById(String accessToken, PupilByIdRequest pupilByIdRequest);
    Observable<ResponseBody> updatePupil(String accessToken, UpdatePupilRequest updatePupilRequest);
}
