package com.dmt.max.schoolschedule.lessons.presenters.listing;

import com.dmt.max.schoolschedule.lessons.interactors.listing.LessonsListingInteractor;
import com.dmt.max.schoolschedule.lessons.views.listing.LessonsListingView;
import com.dmt.max.schoolschedule.model.lesson.Lesson;
import com.dmt.max.schoolschedule.model.lesson.responses.LessonsResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Max on 03.06.2018.
 */

public class LessonsListingPresenterImpl implements LessonsListingPresenter {
    private LessonsListingInteractor lessonsListingInteractor;
    private LessonsListingView view;

    private String accessToken;
    private String refreshToken;

    private List<Lesson> loadedLessons;

    private boolean isUpdateAction;

    public LessonsListingPresenterImpl(LessonsListingInteractor lessonsListingInteractor) {
        this.lessonsListingInteractor = lessonsListingInteractor;
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
    public void deleteLesson(String lessonId) {
        lessonsListingInteractor.deleteLesson(accessToken, lessonId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLessonDeleteSuccess, this::onLessonDeleteFail);
    }

    private void onLessonDeleteSuccess(ResponseBody responseBody) {
        if (isViewAttached()) {
            displayLessons();
            view.onDeleteSuccess();
        }
    }

    private void onLessonDeleteFail(Throwable throwable) {
        if (isViewAttached()) {
            view.onDeleteFailed(throwable.getMessage());
        }
    }

    @Override
    public void setView(LessonsListingView view) {
        this.view = view;
        displayLessons();
    }

    private void displayLessons() {
        showLoading();

        lessonsListingInteractor.getLessons(accessToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLessonsFetchSuccess, this::onLessonsFetchFail);
    }

    private void showLoading() {
        if (isViewAttached()) {
            view.loadingStarted();
        }
    }

    private void onLessonsFetchSuccess(LessonsResponse lessonsResponse) {
        List<Lesson> lessons = lessonsResponse.getLessons();
        loadedLessons = new ArrayList(lessons);
        if (isViewAttached()) {
            view.showLessons(loadedLessons);
        }
    }

    private void onLessonsFetchFail(Throwable throwable) {
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
