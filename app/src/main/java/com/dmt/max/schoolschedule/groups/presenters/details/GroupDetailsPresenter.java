package com.dmt.max.schoolschedule.groups.presenters.details;

import com.dmt.max.schoolschedule.groups.views.details.GroupDetailsView;
import com.dmt.max.schoolschedule.model.group.Group;
import com.dmt.max.schoolschedule.model.group.requests.CreateGroupRequest;

/**
 * Created by Max on 20.05.2018.
 */

public interface GroupDetailsPresenter {
    void setAccessToken(String accessToken);

    void setRefreshToken(String refreshToken);

    void createPupil(CreateGroupRequest createPupilRequest);

    void onActionButtonClick(Group pupil);

    void setView(GroupDetailsView view);

    void destroy();
}
