package com.dmt.max.schoolschedule.pupils.presenters;

import android.content.SharedPreferences;

import com.dmt.max.schoolschedule.R;
import com.dmt.max.schoolschedule.model.pupil.Pupil;
import com.dmt.max.schoolschedule.model.pupil.PupilsResponse;
import com.dmt.max.schoolschedule.pupils.interactors.PupilsListingInteractor;
import com.dmt.max.schoolschedule.pupils.views.PupilsListingView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Max on 13.05.2018.
 */

public class PupilsListingPresenterImpl implements PupilsListingPresenter {
    private PupilsListingView view;
    private PupilsListingInteractor pupilsInteractor;
    private Disposable fetchSubscription;
    private List<Pupil> loadedPupils;

    public PupilsListingPresenterImpl(PupilsListingInteractor pupilsInteractor) {
        this.pupilsInteractor = pupilsInteractor;
    }

    @Override
    public void setView(PupilsListingView view, String accessToken) {
        this.view = view;
        displayPupils(accessToken);
    }

    private void displayPupils(String accessToken) {
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
