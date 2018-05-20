package com.dmt.max.schoolschedule.groups.presenters.details;

import com.dmt.max.schoolschedule.groups.interactors.details.GroupDetailsInteractor;
import com.dmt.max.schoolschedule.groups.views.details.GroupDetailsView;
import com.dmt.max.schoolschedule.model.group.Group;
import com.dmt.max.schoolschedule.model.group.requests.CreateGroupRequest;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Max on 20.05.2018.
 */

public class GroupDetailsPresenterImpl implements GroupDetailsPresenter {
    private GroupDetailsInteractor groupDetailsInteractor;

    private String accessToken;
    private String refreshToken;

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
    public void createPupil(CreateGroupRequest createPupilRequest) {
        groupDetailsInteractor.createGroup(accessToken, createPupilRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onCreateGroupSuccess, this::onCreateGroupFail);
    }

    private void onCreateGroupFail(Throwable throwable) {
     
    }

    private void onCreateGroupSuccess(ResponseBody responseBody) {

    }

    @Override
    public void onActionButtonClick(Group pupil) {

    }

    @Override
    public void setView(GroupDetailsView view) {

    }

    @Override
    public void destroy() {

    }
}
