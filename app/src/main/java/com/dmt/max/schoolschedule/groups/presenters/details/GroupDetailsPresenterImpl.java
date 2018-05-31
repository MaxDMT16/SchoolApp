package com.dmt.max.schoolschedule.groups.presenters.details;

import com.dmt.max.schoolschedule.groups.interactors.details.GroupDetailsInteractor;
import com.dmt.max.schoolschedule.groups.views.details.GroupDetailsView;
import com.dmt.max.schoolschedule.model.group.Group;
import com.dmt.max.schoolschedule.model.group.requests.CreateGroupRequest;
import com.dmt.max.schoolschedule.model.group.requests.GroupByIdRequest;
import com.dmt.max.schoolschedule.model.group.requests.UpdateGroupRequest;
import com.dmt.max.schoolschedule.model.group.responses.GroupResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Max on 20.05.2018.
 */

public class GroupDetailsPresenterImpl implements GroupDetailsPresenter {
    private GroupDetailsInteractor groupDetailsInteractor;
    private GroupDetailsView view;

    private String accessToken;
    private String refreshToken;

    private boolean isUpdateAction;

    public GroupDetailsPresenterImpl(GroupDetailsInteractor groupDetailsInteractor) {
        this.groupDetailsInteractor = groupDetailsInteractor;
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
    public void requestGetGroupById(String groupId) {
        if (groupId == null){
            isUpdateAction = false;
            return;
        }

        GroupByIdRequest groupByIdRequest = new GroupByIdRequest(groupId);

        groupDetailsInteractor.getGroupById(accessToken, groupId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetGroupByIdSuccess, this::onGetGroupByIdFail);
    }

    private void onGetGroupByIdSuccess(GroupResponse groupResponse) {
        isUpdateAction = true;
        if (isViewAttached()){
            view.onRequestGroupByIdSuccess(groupResponse);
        }
    }

    private void onGetGroupByIdFail(Throwable throwable) {
        if (isViewAttached()){
            view.onRequestFail(throwable.getMessage());
        }
    }

    @Override
    public void createGroup(CreateGroupRequest createGroupRequest) {
        groupDetailsInteractor.createGroup(accessToken, createGroupRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onCreateGroupSuccess, this::onCreateGroupFail);
    }

    @Override
    public void updateGroup(UpdateGroupRequest updateGroupRequest) {
        groupDetailsInteractor.updateGroup(accessToken, updateGroupRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onUpdateGroupSuccess, this::onUpdateGroupFail);
    }

    private void onUpdateGroupFail(Throwable throwable) {
        if(isViewAttached()){
            view.onRequestFail(throwable.getMessage());
        }
    }

    private void onUpdateGroupSuccess(ResponseBody responseBody) {
        if (isViewAttached()){
            view.onUpdateGroupSuccess();
        }
    }

    private void onCreateGroupFail(Throwable throwable) {
        if (isViewAttached()) {
            view.onRequestFail(throwable.getMessage());
        }
    }

    private void onCreateGroupSuccess(ResponseBody responseBody) {
        if (isViewAttached()) {
            view.onCreateGroupSuccess();
        }
    }

    @Override
    public void onActionButtonClick(Group group) {
        if (isUpdateAction){
            UpdateGroupRequest updateGroupRequest = getUpdateGroupRequest(group);
            updateGroup(updateGroupRequest);
        }
        else {
            CreateGroupRequest createGroupRequest = getCreateGroupRequest(group);
            createGroup(createGroupRequest);
        }
    }

    private UpdateGroupRequest getUpdateGroupRequest(Group group) {
        UpdateGroupRequest updateGroupRequest = new UpdateGroupRequest();
        updateGroupRequest.setId(group.getId());
        updateGroupRequest.setName(group.getName());
        return updateGroupRequest;
    }

    private CreateGroupRequest getCreateGroupRequest(Group group) {
        CreateGroupRequest createGroupRequest = new CreateGroupRequest();
        createGroupRequest.setName(group.getName());
        return createGroupRequest;
    }

    @Override
    public void setView(GroupDetailsView view) {
        this.view = view;
        if (isViewAttached()){
            accessToken = view.getAccessToken();
            refreshToken = view.getRefreshToken();
        }
    }

    @Override
    public void destroy() {
        this.view = null;
    }

    private boolean isViewAttached() {
        return view != null;
    }
}
