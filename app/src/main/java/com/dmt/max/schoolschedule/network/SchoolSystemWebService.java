package com.dmt.max.schoolschedule.network;

import com.dmt.max.schoolschedule.model.group.requests.CreateGroupRequest;
import com.dmt.max.schoolschedule.model.group.requests.UpdateGroupRequest;
import com.dmt.max.schoolschedule.model.group.responses.GroupResponse;
import com.dmt.max.schoolschedule.model.group.responses.GroupsResponse;
import com.dmt.max.schoolschedule.model.login.LoginRequest;
import com.dmt.max.schoolschedule.model.login.SuccessLoginResponse;
import com.dmt.max.schoolschedule.model.pupil.requests.CreatePupilRequest;
import com.dmt.max.schoolschedule.model.pupil.requests.UpdatePupilRequest;
import com.dmt.max.schoolschedule.model.pupil.resoponses.PupilResponse;
import com.dmt.max.schoolschedule.model.pupil.resoponses.PupilsResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Max on 29.04.2018.
 */

public interface SchoolSystemWebService {

    @POST("public/cms-login")
    Call<SuccessLoginResponse> login(@Body LoginRequest loginRequest);

    //------------------pupil-------------------------------
    @GET("admin/pupil")
    Observable<PupilsResponse> getPupils(@Header("Authorization") String accessToken);

    @GET("admin/pupil/{id}")
    Observable<PupilResponse> getPupilById(@Header("Authorization") String accessToken, @Path("id") String pupilId);

    @POST("admin/pupil")
    Observable<ResponseBody> createPupil(@Header("Authorization") String accessToken, @Body CreatePupilRequest createPupilRequest);

    @DELETE("admin/pupil")
    Observable<ResponseBody> deletePupil(@Header("Authorization") String accessToken, @Query("id") String pupilId);

    @PUT("admin/pupil")
    Observable<ResponseBody> updatePupil(@Header("Authorization") String accessToken, @Body UpdatePupilRequest updatePupilRequest);

    //-------------------group------------------------------------------

    @GET("admin/group")
    Observable<GroupsResponse> getGroups(@Header("Authorization") String accessToken);

    @GET("admin/group/{id}")
    Observable<GroupResponse> getGroupById(@Header("Authorization") String accessToken, @Path("id") String groupId);

    @DELETE("admin/group")
    Observable<ResponseBody> deleteGroup(@Header("Authorization") String accessToken, @Query("id") String groupId);

    @POST("admin/group")
    Observable<ResponseBody> createGroup(@Header("Authorization") String accessToken, @Body CreateGroupRequest createGroupRequest);

    @PUT("admin/group")
    Observable<ResponseBody> updateGroup(@Header("Authorization") String accessToken, @Body UpdateGroupRequest updateGroupRequest);
}
