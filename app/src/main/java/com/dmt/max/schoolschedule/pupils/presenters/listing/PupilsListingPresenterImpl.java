package com.dmt.max.schoolschedule.pupils.presenters.listing;

import com.dmt.max.schoolschedule.model.pupil.Pupil;
import com.dmt.max.schoolschedule.model.pupil.resoponses.PupilsResponse;
import com.dmt.max.schoolschedule.pupils.interactors.listing.PupilsListingInteractor;
import com.dmt.max.schoolschedule.pupils.views.listing.PupilsListingView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Max on 13.05.2018.
 */

public class PupilsListingPresenterImpl implements PupilsListingPresenter {
    private PupilsListingView view;
    private PupilsListingInteractor pupilsInteractor;
    private Disposable fetchSubscription;
    private List<Pupil> loadedPupils;
    private String accessToken;
    private String refreshToken;

    public PupilsListingPresenterImpl(PupilsListingInteractor pupilsInteractor) {
        this.pupilsInteractor = pupilsInteractor;
    }

    @Override
    public void setView(PupilsListingView view) {
        this.view = view;
        displayPupils();
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
    public void deletePupil(String pupilId) {
        pupilsInteractor.deletePupil(accessToken, pupilId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::onDeleteSuccess, this::onDeleteFail);
    }

    private void onDeleteFail(Throwable throwable) {
        if (isViewAttached()){
            view.onDeleteFailed(throwable.getMessage());
        }
    }

    private void onDeleteSuccess(ResponseBody responseBody) {
        if(isViewAttached()){
            displayPupils();
            view.onDeleteSuccess();
        }
    }

    private void displayPupils() {
        showLoading();

        fetchSubscription = pupilsInteractor.getPupils(accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onPupilsFetchSuccess, this::onPupilsFetchFailed);
    }

    private void showLoading() {
        if (isViewAttached()) {
            view.loadingStarted();
        }
    }

    private boolean isViewAttached() {
        return view != null;
    }

    private void onPupilsFetchSuccess(PupilsResponse pupilsResponse) {
        List<Pupil> pupils = pupilsResponse.getPupils();
        loadedPupils = new ArrayList<>(pupils);
        if (isViewAttached()) {
            view.showPupils(loadedPupils);
        }
    }

    private void onPupilsFetchFailed(Throwable e) {
        view.loadingFailed(e.getMessage());
    }

    @Override
    public void destroy() {
        view = null;
        //todo: unsubscribe pupils fetching
    }
}
