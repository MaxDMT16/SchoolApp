package com.dmt.max.schoolschedule.groups.views.listing;

import com.dmt.max.schoolschedule.model.group.Group;

import java.util.List;

/**
 * Created by Max on 20.05.2018.
 */

public interface GroupsListingView {
    void loadingStarted();
    void showGroups(List<Group> groups);
    void loadingFailed(String errorMessage);
    void deleteGroup(String groupId);
    void onDeleteSuccess();
    void onDeleteFailed(String message);
    void onGroupClick(String groupId);
}
