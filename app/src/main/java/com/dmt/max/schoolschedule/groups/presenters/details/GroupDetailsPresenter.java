package com.dmt.max.schoolschedule.groups.presenters.details;

import com.dmt.max.schoolschedule.groups.views.details.GroupDetailsView;
import com.dmt.max.schoolschedule.model.group.Group;
import com.dmt.max.schoolschedule.model.group.requests.CreateGroupRequest;
import com.dmt.max.schoolschedule.model.group.requests.UpdateGroupRequest;

/**
 * Created by Max on 20.05.2018.
 */

public interface GroupDetailsPresenter {
    void setAccessToken(String accessToken);

    void setRefreshToken(String refreshToken);

    void createGroup(CreateGroupRequest createGroupRequest);

    void updateGroup(UpdateGroupRequest updateGroupRequest);

    void requestGetGroupById(String groupId);

    void onActionButtonClick(Group group);

    void setView(GroupDetailsView view);

    void destroy();
}
