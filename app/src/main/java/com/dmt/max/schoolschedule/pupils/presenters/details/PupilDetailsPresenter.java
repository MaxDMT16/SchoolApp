package com.dmt.max.schoolschedule.pupils.presenters.details;

import com.dmt.max.schoolschedule.model.pupil.requests.CreatePupilRequest;
import com.dmt.max.schoolschedule.model.pupil.Pupil;
import com.dmt.max.schoolschedule.model.pupil.requests.UpdatePupilRequest;
import com.dmt.max.schoolschedule.pupils.views.details.PupilDetailsView;

/**
 * Created by Max on 19.05.2018.
 */

public interface PupilDetailsPresenter {
    void setAccessToken(String accessToken);
    void setRefreshToken(String refreshToken);
    void createPupil(CreatePupilRequest createPupilRequest);
    void updatePupil(UpdatePupilRequest updatePupilRequest);
    void requestGetPupilById(String pupilId);
    void onActionButtonClick(Pupil pupil);
    void setView(PupilDetailsView view);
    void destroy();
}
