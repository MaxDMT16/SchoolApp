package com.dmt.max.schoolschedule.groups.interactors.details;

import com.dmt.max.schoolschedule.model.group.requests.CreateGroupRequest;
import com.dmt.max.schoolschedule.model.group.requests.UpdateGroupRequest;
import com.dmt.max.schoolschedule.model.group.responses.GroupResponse;
import com.dmt.max.schoolschedule.network.SchoolSystemWebService;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Max on 20.05.2018.
 */

public class GroupDetailsInteractorImpl implements GroupDetailsInteractor {
    private SchoolSystemWebService schoolSystemWebService;

    public GroupDetailsInteractorImpl(SchoolSystemWebService schoolSystemWebService) {
        this.schoolSystemWebService = schoolSystemWebService;
    }

    @Override
    public Observable<ResponseBody> createGroup(String accessToken, CreateGroupRequest createGroupRequest) {
        return schoolSystemWebService.createGroup(accessToken, createGroupRequest);
    }

    @Override
    public Observable<ResponseBody> updateGroup(String accessToken, UpdateGroupRequest updateGroupRequest) {
        return schoolSystemWebService.updateGroup(accessToken, updateGroupRequest);
    }

    @Override
    public Observable<GroupResponse> getGroupById(String accessToken, String groupId) {
        return schoolSystemWebService.getGroupById(accessToken, groupId);
    }
}
