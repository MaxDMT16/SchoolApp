package com.dmt.max.schoolschedule.teachers.interactors.listing;

import com.dmt.max.schoolschedule.model.teachers.responses.TeachersResponse;
import com.dmt.max.schoolschedule.network.SchoolSystemWebService;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Max on 02.06.2018.
 */

public class TeachersListingInteractorImpl implements TeachersListingInteractor {
    private SchoolSystemWebService schoolSystemWebService;

    public TeachersListingInteractorImpl(SchoolSystemWebService schoolSystemWebService) {
        this.schoolSystemWebService = schoolSystemWebService;
    }

    @Override
    public Observable<TeachersResponse> getTeachers(String accessToken) {
        return schoolSystemWebService.getTeachers(accessToken);
    }

    @Override
    public Observable<ResponseBody> deleteTeacher(String accessToken, String teacherId) {
        return schoolSystemWebService.deleteTeacher(accessToken, teacherId);
    }
}
