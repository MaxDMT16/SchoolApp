package com.dmt.max.schoolschedule.groups.interactors.listing;

import com.dmt.max.schoolschedule.model.group.responses.GroupsResponse;
import com.dmt.max.schoolschedule.network.SchoolSystemWebService;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Max on 20.05.2018.
 */

public class GroupsListingInteractorImpl implements GroupsListingInteractor {
    private SchoolSystemWebService schoolSystemWebService;

    public GroupsListingInteractorImpl(SchoolSystemWebService schoolSystemWebService) {
        this.schoolSystemWebService = schoolSystemWebService;
    }

    @Override
    public Observable<ResponseBody> deleteGroup(String accessToken, String groupId) {
        return schoolSystemWebService.deleteGroup(accessToken, groupId);
    }

    @Override
    public Observable<GroupsResponse> getGroups(String accessToken) {
        return schoolSystemWebService.getGroups(accessToken);
    }
}
