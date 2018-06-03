package com.dmt.max.schoolschedule.teachers.presenters.details;

import com.dmt.max.schoolschedule.model.teachers.Teacher;
import com.dmt.max.schoolschedule.model.teachers.requests.CreateTeacherRequest;
import com.dmt.max.schoolschedule.model.teachers.requests.UpdateTeacherRequest;
import com.dmt.max.schoolschedule.model.teachers.responses.TeacherResponse;
import com.dmt.max.schoolschedule.teachers.interactors.details.TeacherDetailsInteractor;
import com.dmt.max.schoolschedule.teachers.views.details.TeacherDetailsView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Max on 02.06.2018.
 */

public class TeacherDetailsPresenterImpl implements TeacherDetailsPresenter {
    private TeacherDetailsInteractor teacherDetailsInteractor;
    private TeacherDetailsView view;

    private String accessToken;
    private String refreshToken;

    private boolean isUpdateAction;

    public TeacherDetailsPresenterImpl(TeacherDetailsInteractor teacherDetailsInteractor) {
        this.teacherDetailsInteractor = teacherDetailsInteractor;
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
    public void createTeacher(CreateTeacherRequest createTeacherRequest) {
        teacherDetailsInteractor.createTeacher(accessToken, createTeacherRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onCreateTeacherSuccess, this::onCreateTeacherFail);
    }

    private void onCreateTeacherFail(Throwable throwable) {
        if (isViewAttached()) {
            view.onRequestFail(throwable.getMessage());
        }
    }

    private void onCreateTeacherSuccess(ResponseBody responseBody) {
        if (isViewAttached()) {
            view.onCreateTeacherSuccess();
        }
    }

    @Override
    public void updateTeacher(UpdateTeacherRequest updateTeacherRequest) {
        teacherDetailsInteractor.updateTeacher(accessToken, updateTeacherRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onUpdateTeacherSuccess, this::onUpdateTeacherFail);
    }

    private void onUpdateTeacherSuccess(ResponseBody responseBody) {
        if (isViewAttached()){
            view.onUpdateTeacherSuccess();
        }
    }

    private void onUpdateTeacherFail(Throwable throwable) {
        if(isViewAttached()){
            view.onRequestFail(throwable.getMessage());
        }
    }

    @Override
    public void requestGetTeacherById(String teacherId) {
        if (teacherId == null){
            isUpdateAction = false;
            return;
        }

        teacherDetailsInteractor.getTeacherById(accessToken, teacherId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetTeacherByIdSuccess, this::onGetTeacherByIdFail);
    }

    private void onGetTeacherByIdSuccess(TeacherResponse teacherResponse) {
        isUpdateAction = true;
        if (isViewAttached()){
            view.onRequestTeacherByIdSuccess(teacherResponse);
        }
    }

    private void onGetTeacherByIdFail(Throwable throwable) {
        if (isViewAttached()){
            view.onRequestFail(throwable.getMessage());
        }
    }

    @Override
    public void onActionButtonClick(Teacher teacher) {
        if (isUpdateAction){
            UpdateTeacherRequest updateTeacherRequest = getUpdateGroupRequest(teacher);
            updateTeacher(updateTeacherRequest);
        }
        else {
            CreateTeacherRequest createTeacherRequest = getCreateGroupRequest(teacher);
            createTeacher(createTeacherRequest);
        }
    }

    private CreateTeacherRequest getCreateGroupRequest(Teacher teacher) {
        CreateTeacherRequest createTeacherRequest = new CreateTeacherRequest();
        createTeacherRequest.setFirstName(teacher.getFirstName());
        createTeacherRequest.setLastName(teacher.getLastName());

        return createTeacherRequest;
    }

    private UpdateTeacherRequest getUpdateGroupRequest(Teacher teacher) {
        UpdateTeacherRequest updateTeacherRequest = new UpdateTeacherRequest();
        updateTeacherRequest.setId(teacher.getId());
        updateTeacherRequest.setFirstName(teacher.getFirstName());
        updateTeacherRequest.setLastName(teacher.getLastName());

        return updateTeacherRequest;
    }

    @Override
    public void setView(TeacherDetailsView view) {
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
