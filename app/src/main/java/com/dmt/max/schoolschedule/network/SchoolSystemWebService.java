package com.dmt.max.schoolschedule.network;

import com.dmt.max.schoolschedule.model.login.LoginRequest;
import com.dmt.max.schoolschedule.model.login.SuccessLoginResponse;
import com.dmt.max.schoolschedule.model.pupil.PupilsResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Max on 29.04.2018.
 */

public interface SchoolSystemWebService {

    @POST("public/cms-login")
    Call<SuccessLoginResponse> login(@Body LoginRequest loginRequest);

    @GET("admin/pupil")
    Observable<PupilsResponse> getPupils(@Header("Authorization") String accessToken);
}
