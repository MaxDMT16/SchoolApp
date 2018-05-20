package com.dmt.max.schoolschedule.groups.presenters.listing;

import com.dmt.max.schoolschedule.groups.interactors.listing.GroupsListingInteractor;
import com.dmt.max.schoolschedule.groups.views.listing.GroupsListingView;
import com.dmt.max.schoolschedule.model.group.Group;
import com.dmt.max.schoolschedule.model.group.responses.GroupsResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
        this.view = view;
        displayGroups();
    }

    private void displayGroups() {
        showLoading();

        groupsListingInteractor.getGroups(accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGroupsFetchSuccess, this::onGroupsFetchFail);
    }

    private void onGroupsFetchFail(Throwable throwable) {
        if (isViewAttached()){
            view.loadingFailed(throwable.getMessage());
        }
    }

    private void onGroupsFetchSuccess(GroupsResponse groupsResponse) {
        List<Group> groups = groupsResponse.getGroups();
        loadedGroups = new ArrayList<>(groups);
        if (isViewAttached()){
            view.showGroups(loadedGroups);
        }
    }

    private void showLoading() {
        if (isViewAttached()){
            view.loadingStarted();
        }
    }

    private boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void destroy() {
        view = null;
    }
}
