package com.dmt.max.schoolschedule.groups.presenters.listing;

import com.dmt.max.schoolschedule.groups.views.listing.GroupsListingView;

/**
 * Created by Max on 20.05.2018.
 */

public interface GroupsListingPresenter {
    void setAccessToken(String accessToken);
    void setRefreshToken(String refreshToken);
    void deleteGroup(String groupId);
    void setView(GroupsListingView view);
    void destroy();
}
