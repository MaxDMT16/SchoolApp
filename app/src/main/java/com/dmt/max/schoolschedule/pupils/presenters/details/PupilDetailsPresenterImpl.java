package com.dmt.max.schoolschedule.pupils.presenters.details;

import com.dmt.max.schoolschedule.model.group.responses.GroupsResponse;
import com.dmt.max.schoolschedule.model.pupil.requests.CreatePupilRequest;
import com.dmt.max.schoolschedule.model.pupil.Pupil;
import com.dmt.max.schoolschedule.model.pupil.requests.PupilByIdRequest;
import com.dmt.max.schoolschedule.model.pupil.requests.UpdatePupilRequest;
import com.dmt.max.schoolschedule.model.pupil.resoponses.PupilResponse;
import com.dmt.max.schoolschedule.pupils.interactors.details.PupilDetailsInteractor;
import com.dmt.max.schoolschedule.pupils.views.details.PupilDetailsView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Max on 19.05.2018.
 */

public class PupilDetailsPresenterImpl implements PupilDetailsPresenter {
    private PupilDetailsInteractor pupilDetailsInteractor;
    private String accessToken;
    private String refreshToken;

    private PupilDetailsView view;

    private boolean isUpdateAction;

    public PupilDetailsPresenterImpl(PupilDetailsInteractor pupilDetailsInteractor) {
        this.pupilDetailsInteractor = pupilDetailsInteractor;
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
    public void createPupil(CreatePupilRequest createPupilRequest) {
        pupilDetailsInteractor.createPupil(accessToken, createPupilRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onCreatePupilSuccess, this::onCreatePupilFail);
    }

    @Override
    public void updatePupil(UpdatePupilRequest updatePupilRequest) {
        pupilDetailsInteractor.updatePupil(accessToken, updatePupilRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onUpdatePupilSuccess, this::onUpdatePupilFail);
    }

    private void onUpdatePupilFail(Throwable throwable) {
        if (isViewAttached()){
            view.onRequestFail(throwable.getMessage());
        }
    }

    private void onUpdatePupilSuccess(ResponseBody responseBody) {
        if (isViewAttached()){
            view.onUpdatePupilSuccess();
        }
    }

    @Override
    public void requestGetPupilById(String pupilId) {
        if (pupilId == null) {
            isUpdateAction = false;
            return;
        }

        PupilByIdRequest pupilByIdRequest = new PupilByIdRequest(pupilId);

        pupilDetailsInteractor.getPupilById(accessToken, pupilByIdRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetPupilSuccess, this::onGetPupilFailure);
    }

    @Override
    public void onActionButtonClick(Pupil pupil) {
        if (isUpdateAction) {
            UpdatePupilRequest updatePupilRequest = getUpdatePupilRequest(pupil);
            updatePupil(updatePupilRequest);
        } else {
            CreatePupilRequest createPupilRequest = getCreatePupilRequest(pupil);
            createPupil(createPupilRequest);
        }
    }

    private UpdatePupilRequest getUpdatePupilRequest(Pupil pupil) {
        UpdatePupilRequest updatePupilRequest = new UpdatePupilRequest();
        updatePupilRequest.setId(pupil.getId());
        updatePupilRequest.setFirstName(pupil.getFirstName());
        updatePupilRequest.setLastName(pupil.getLastName());
        updatePupilRequest.setGroupId(pupil.getGroupId());

        return updatePupilRequest;
    }

    private CreatePupilRequest getCreatePupilRequest(Pupil pupil) {
        CreatePupilRequest createPupilRequest = new CreatePupilRequest();
        createPupilRequest.setFirstName(pupil.getFirstName());
        createPupilRequest.setLastName(pupil.getLastName());
        createPupilRequest.setGroupId(pupil.getGroupId());

        return createPupilRequest;
    }

    private void onGetPupilFailure(Throwable throwable) {
        if (isViewAttached()) {
            view.onRequestFail(throwable.getMessage());
        }
    }

    private void onGetPupilSuccess(PupilResponse pupilResponse) {
        isUpdateAction = true;
        if (isViewAttached()) {
            view.onRequestPupilByIdSuccess(pupilResponse);
        }
    }

    private void onCreatePupilFail(Throwable throwable) {
        if (isViewAttached()) {
            view.onRequestFail(throwable.getMessage());
        }
    }

    private void onCreatePupilSuccess(ResponseBody responseBody) {
        if (isViewAttached()) {
            view.onCreatePupilSuccess();
        }
    }

    @Override
    public void setView(PupilDetailsView view) {
        this.view = view;
        if (isViewAttached()) {
            accessToken = view.getAccessToken();
            refreshToken = view.getRefreshToken();
        }

        fetchGroups();
    }

    private void fetchGroups(){
        pupilDetailsInteractor.getGroups(accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onFetchGroupsSuccess, this::onFetchGroupsFail);
    }

    private void onFetchGroupsSuccess(GroupsResponse groupsResponse) {
        if (isViewAttached()){
            view.onGroupsFetchSuccess(groupsResponse);
        }
    }

    private void onFetchGroupsFail(Throwable throwable) {
        if (isViewAttached()){
            view.onRequestFail(throwable.getMessage());
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
