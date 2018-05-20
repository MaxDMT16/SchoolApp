package com.dmt.max.schoolschedule.groups.presenters.listing;

import com.dmt.max.schoolschedule.groups.interactors.listing.GroupsListingInteractor;
import com.dmt.max.schoolschedule.groups.views.listing.GroupsListingView;
import com.dmt.max.schoolschedule.model.group.Group;

import java.util.List;

/**
 * Created by Max on 20.05.2018.
 */

public class GroupsListingPresenterImpl implements GroupsListingPresenter {
    private String accessToken;
    private String refreshToken;

    private GroupsListingView view;
    private GroupsListingInteractor groupsListingInteractor;
    private List<Group> loadedGroups;

    public GroupsListingPresenterImpl(GroupsListingInteractor groupsListingInteractor) {
        this.groupsListingInteractor = groupsListingInteractor;
    }


    @Override
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public void deleteGroup(String groupId) {

    }

    @Override
    public void setView(GroupsListingView view) {

    }

    @Override
    public void destroy() {

    }
}
