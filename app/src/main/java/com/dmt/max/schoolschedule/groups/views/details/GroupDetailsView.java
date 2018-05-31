package com.dmt.max.schoolschedule.groups.views.details;

import com.dmt.max.schoolschedule.model.group.responses.GroupResponse;
/**
 * Created by Max on 20.05.2018.
 */

public interface GroupDetailsView {
    void onCreateGroupSuccess();
    void onUpdateGroupSuccess();
    void onRequestFail(String message);
    void onRequestGroupByIdSuccess(GroupResponse groupResponse);
    String getAccessToken();
    String getRefreshToken();
}
