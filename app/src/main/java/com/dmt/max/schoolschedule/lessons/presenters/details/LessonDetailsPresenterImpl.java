package com.dmt.max.schoolschedule.lessons.presenters.details;

import com.dmt.max.schoolschedule.lessons.interactors.details.LessonDetailsInteractor;
import com.dmt.max.schoolschedule.lessons.views.details.LessonDetailsView;
import com.dmt.max.schoolschedule.model.lesson.Lesson;
import com.dmt.max.schoolschedule.model.lesson.requests.CreateLessonRequest;
import com.dmt.max.schoolschedule.model.lesson.requests.UpdateLessonRequest;
import com.dmt.max.schoolschedule.model.lesson.responses.LessonResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Max on 03.06.2018.
 */

public class LessonDetailsPresenterImpl implements LessonDetailsPresenter {
    private LessonDetailsInteractor lessonDetailsInteractor;
    private LessonDetailsView view;

    private String accessToken;
    private String refreshToken;

    private boolean isUpdateAction;

    public LessonDetailsPresenterImpl(LessonDetailsInteractor lessonDetailsInteractor) {
        this.lessonDetailsInteractor = lessonDetailsInteractor;
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
    public void createLesson(CreateLessonRequest createLessonRequest) {
        lessonDetailsInteractor.createLesson(accessToken, createLessonRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onCreateLessonSuccess, this::onCreateLessonFail);
    }

    private void onCreateLessonSuccess(ResponseBody responseBody) {
        if (isViewAttached()) {
            view.onCreateLessonSuccess();
        }
    }

    private void onCreateLessonFail(Throwable throwable) {
        if (isViewAttached()) {
            view.onRequestFail(throwable.getMessage());
        }
    }

    @Override
    public void updateLesson(UpdateLessonRequest updateLessonRequest) {
        lessonDetailsInteractor.updateLesson(accessToken, updateLessonRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onUpdateLessonSuccess, this::onUpdateLessonFail);
    }

    private void onUpdateLessonSuccess(ResponseBody responseBody) {
        if (isViewAttached()) {
            view.onUpdateLessonSuccess();
        }
    }

    private void onUpdateLessonFail(Throwable throwable) {
        if (isViewAttached()) {
            view.onRequestFail(throwable.getMessage());
        }
    }

    @Override
    public void requestGetLessonById(String lessonId) {
        if (lessonId == null) {
            isUpdateAction = false;
            return;
        }

        lessonDetailsInteractor.getLessonById(accessToken, lessonId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetLessonByIdSuccess, this::onGetLessonByIdFail);
    }

    private void onGetLessonByIdSuccess(LessonResponse lessonResponse) {
        isUpdateAction = true;
        if (isViewAttached()) {
            view.onRequestLessonByIdSuccess(lessonResponse);
        }
    }

    private void onGetLessonByIdFail(Throwable throwable) {
        if (isViewAttached()) {
            view.onRequestFail(throwable.getMessage());
        }
    }

    @Override
    public void onActionButtonClick(Lesson lesson) {
        if (isUpdateAction) {
            UpdateLessonRequest updateLessonRequest = getUpdateLessonRequest(lesson);
            updateLesson(updateLessonRequest);
        } else {
            CreateLessonRequest createLessonRequest = getCreateLessonRequest(lesson);
            createLesson(createLessonRequest);
        }
    }

    private CreateLessonRequest getCreateLessonRequest(Lesson lesson) {
        CreateLessonRequest createLessonRequest = new CreateLessonRequest();
        createLessonRequest.setGroupId(lesson.getGroupId());
        createLessonRequest.setTeacherId(lesson.getTeacherId());
        createLessonRequest.setSubject(lesson.getSubject());

        return createLessonRequest;
    }

    private UpdateLessonRequest getUpdateLessonRequest(Lesson lesson) {
        UpdateLessonRequest updateLessonRequest = new UpdateLessonRequest();
        updateLessonRequest.setId(lesson.getId());
        updateLessonRequest.setGroupId(lesson.getGroupId());
        updateLessonRequest.setTeacherId(lesson.getTeacherId());
        updateLessonRequest.setSubject(lesson.getSubject());

        return updateLessonRequest;
    }

    @Override
    public void setView(LessonDetailsView view) {
        this.view = view;
        if (isViewAttached()) {
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
