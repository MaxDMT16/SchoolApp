package com.dmt.max.schoolschedule.pupils.views.details;

import com.dmt.max.schoolschedule.model.group.responses.GroupsResponse;
import com.dmt.max.schoolschedule.model.pupil.resoponses.PupilResponse;

/**
 * Created by Max on 19.05.2018.
 */

public interface PupilDetailsView {
    void onCreatePupilSuccess();
    void onUpdatePupilSuccess();
    void onRequestFail(String message);
    void onRequestPupilByIdSuccess(PupilResponse pupilResponse);
    String getAccessToken();
    String getRefreshToken();

    void onGroupsFetchSuccess(GroupsResponse groupsResponse);
}
