package com.dmt.max.schoolschedule.teachers.presenters.listing;

import com.dmt.max.schoolschedule.model.teachers.Teacher;
import com.dmt.max.schoolschedule.model.teachers.responses.TeachersResponse;
import com.dmt.max.schoolschedule.teachers.interactors.listing.TeachersListingInteractor;
import com.dmt.max.schoolschedule.teachers.views.listing.TeachersListingView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Max on 02.06.2018.
 */

public class TeachersListingPresenterImpl implements TeachersListingPresenter {
    private TeachersListingInteractor teachersListingInteractor;

    private String accessToken;
    private String refreshToken;

    private TeachersListingView view;

    private List<Teacher> loadedTeachers;

    public TeachersListingPresenterImpl(TeachersListingInteractor teachersListingInteractor) {
        this.teachersListingInteractor = teachersListingInteractor;
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
    public void deleteTeacher(String teacherId) {
        teachersListingInteractor.deleteTeacher(accessToken, teacherId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onDeleteTeacherSuccess, this::onDeleteTeacherFail);

    }

    private void onDeleteTeacherFail(Throwable throwable) {
        if (isViewAttached()) {
            view.onDeleteFailed(throwable.getMessage());
        }
    }

    private void onDeleteTeacherSuccess(ResponseBody responseBody) {
        if (isViewAttached()) {
            displayTeachers();
            view.onDeleteSuccess();
        }
    }

    @Override
    public void setView(TeachersListingView view) {
        this.view = view;
        displayTeachers();
    }

    private void displayTeachers() {
        showLoading();

        teachersListingInteractor.getTeachers(accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onTeacherssFetchSuccess, this::onTeachersFetchFail);
    }

    private void onTeachersFetchFail(Throwable throwable) {
        if (isViewAttached()) {
            view.loadingFailed(throwable.getMessage());
        }
    }

    private void onTeacherssFetchSuccess(TeachersResponse teachersResponse) {
        List<Teacher> teachers = teachersResponse.getTeachers();
        loadedTeachers = new ArrayList<>(teachers);
        if (isViewAttached()) {
            view.showTeachers(loadedTeachers);
        }
    }

    private void showLoading() {
        if (isViewAttached()) {
            view.loadingStarted();
        }
    }

    @Override
    public void destroy() {
        view = null;
    }

    private boolean isViewAttached() {
        return view != null;
    }
}
