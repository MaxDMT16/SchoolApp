package com.dmt.max.schoolschedule.schedule_cells.presenters.listing;

import com.dmt.max.schoolschedule.model.schedule_cell.ScheduleCell;
import com.dmt.max.schoolschedule.model.schedule_cell.responses.ScheduleCellsResponse;
import com.dmt.max.schoolschedule.schedule_cells.interactors.listing.ScheduleCellsListingInteractor;
import com.dmt.max.schoolschedule.schedule_cells.presenters.listing.ScheduleCellsListingPresenter;
import com.dmt.max.schoolschedule.schedule_cells.views.listing.ScheduleCellsListingView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Max on 09.06.2018.
 */

public class ScheduleCellsListingPresenterImpl implements ScheduleCellsListingPresenter {
    private ScheduleCellsListingInteractor scheduleCellsListingInteractor;
    private ScheduleCellsListingView view;

    private String accessToken;
    private String refreshToken;

    private List<ScheduleCell> loadedCells;

    private boolean isUpdateState;

    public ScheduleCellsListingPresenterImpl(ScheduleCellsListingInteractor scheduleCellsListingInteractor) {
        this.scheduleCellsListingInteractor = scheduleCellsListingInteractor;
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
    public void deleteScheduleCell(String cellId) {
        scheduleCellsListingInteractor.deleteScheduleCell(accessToken, cellId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onScheduleCellDeleteSuccess, this::onScheduleCellDeleteFail);
    }

    private void onScheduleCellDeleteSuccess(ResponseBody responseBody) {
        if (isViewAttached()) {
            displayCells();
            view.onDeleteSuccess();
        }
    }

    private void onScheduleCellDeleteFail(Throwable throwable) {
        if (isViewAttached()) {
            view.onDeleteFailed(throwable.getMessage());
        }
    }

    @Override
    public void setView(ScheduleCellsListingView view) {
        this.view = view;
        displayCells();
    }

    private void displayCells() {
        showLoading();

        scheduleCellsListingInteractor.getSchedulCells(accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onScheduleCellsFetchSuccess, this::onScheduleCellsFetchFail);
    }

    private void showLoading() {
        if (isViewAttached()) {
            view.loadingStarted();
        }
    }

    private void onScheduleCellsFetchSuccess(ScheduleCellsResponse scheduleCellsResponse) {
        List<ScheduleCell> scheduleCells = scheduleCellsResponse.getScheduleCells();
        loadedCells = new ArrayList(scheduleCells);
        if (isViewAttached()) {
            view.showScheduleCells(loadedCells);
        }
    }

    private void onScheduleCellsFetchFail(Throwable throwable) {
        if (isViewAttached()) {
            view.loadingFailed(throwable.getMessage());
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
