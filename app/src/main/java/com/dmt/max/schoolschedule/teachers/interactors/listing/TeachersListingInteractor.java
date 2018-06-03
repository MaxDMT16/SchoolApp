package com.dmt.max.schoolschedule.teachers.interactors.listing;

import com.dmt.max.schoolschedule.model.teachers.responses.TeachersResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Max on 02.06.2018.
 */

public interface TeachersListingInteractor {
    Observable<TeachersResponse> getTeachers(String accessToken);
    Observable<ResponseBody> deleteTeacher(String accessToken, String teacherId);
}
