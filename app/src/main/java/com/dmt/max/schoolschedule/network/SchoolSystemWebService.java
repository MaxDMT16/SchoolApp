package com.dmt.max.schoolschedule.network;

import com.dmt.max.schoolschedule.model.group.requests.CreateGroupRequest;
import com.dmt.max.schoolschedule.model.group.requests.UpdateGroupRequest;
import com.dmt.max.schoolschedule.model.group.responses.GroupResponse;
import com.dmt.max.schoolschedule.model.group.responses.GroupsResponse;
import com.dmt.max.schoolschedule.model.lesson.requests.CreateLessonRequest;
import com.dmt.max.schoolschedule.model.lesson.requests.UpdateLessonRequest;
import com.dmt.max.schoolschedule.model.lesson.responses.LessonResponse;
import com.dmt.max.schoolschedule.model.lesson.responses.LessonsResponse;
import com.dmt.max.schoolschedule.model.login.LoginRequest;
import com.dmt.max.schoolschedule.model.login.SuccessLoginResponse;
import com.dmt.max.schoolschedule.model.pupil.requests.CreatePupilRequest;
import com.dmt.max.schoolschedule.model.pupil.requests.UpdatePupilRequest;
import com.dmt.max.schoolschedule.model.pupil.resoponses.PupilResponse;
import com.dmt.max.schoolschedule.model.pupil.resoponses.PupilsResponse;
import com.dmt.max.schoolschedule.model.schedule_cell.requests.CreateScheduleCellRequest;
import com.dmt.max.schoolschedule.model.schedule_cell.requests.UpdateScheduleCellRequest;
import com.dmt.max.schoolschedule.model.schedule_cell.responses.ScheduleCellResponse;
import com.dmt.max.schoolschedule.model.schedule_cell.responses.ScheduleCellsResponse;
import com.dmt.max.schoolschedule.model.teachers.requests.CreateTeacherRequest;
import com.dmt.max.schoolschedule.model.teachers.requests.UpdateTeacherRequest;
import com.dmt.max.schoolschedule.model.teachers.responses.TeacherResponse;
import com.dmt.max.schoolschedule.model.teachers.responses.TeachersResponse;

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

    //-------------------teacher------------------------------------------

    @GET("admin/teacher")
    Observable<TeachersResponse> getTeachers(@Header("Authorization") String accessToken);

    @GET("admin/teacher/{id}")
    Observable<TeacherResponse> getTeacherById(@Header("Authorization") String accessToken, @Path("id") String teacherId);

    @DELETE("admin/teacher")
    Observable<ResponseBody> deleteTeacher(@Header("Authorization") String accessToken, @Query("id") String teacherId);

    @POST("admin/teacher")
    Observable<ResponseBody> createTeacher(@Header("Authorization") String accessToken, @Body CreateTeacherRequest createTeacherRequest);

    @PUT("admin/teacher")
    Observable<ResponseBody> updateTeacher(@Header("Authorization") String accessToken, @Body UpdateTeacherRequest updateTeacherRequest);

    //-------------------lesson------------------------------------------

    @GET("admin/lesson")
    Observable<LessonsResponse> getLessons(@Header("Authorization") String accessToken);

    @GET("admin/lesson/{id}")
    Observable<LessonResponse> getLessonById(@Header("Authorization") String accessToken, @Path("id") String lessonId);

    @DELETE("admin/lesson")
    Observable<ResponseBody> deleteLesson(@Header("Authorization") String accessToken, @Query("id") String lessonId);

    @POST("admin/lesson")
    Observable<ResponseBody> createLesson(@Header("Authorization") String accessToken, @Body CreateLessonRequest createLessonRequest);

    @PUT("admin/lesson")
    Observable<ResponseBody> updateLesson(@Header("Authorization") String accessToken, @Body UpdateLessonRequest updateLessonRequest);

    //-------------------schedule-cell------------------------------------------

    @GET("admin/schedule-cell")
    Observable<ScheduleCellsResponse> getScheduleCells(@Header("Authorization") String accessToken);

    @GET("admin/schedule-cell/{id}")
    Observable<ScheduleCellResponse> getScheduleCellById(@Header("Authorization") String accessToken, @Path("id") String scheduleCellId);

    @DELETE("admin/schedule-cell")
    Observable<ResponseBody> deleteScheduleCell(@Header("Authorization") String accessToken, @Query("id") String scheduleCellId);

    @POST("admin/schedule-cell")
    Observable<ResponseBody> createScheduleCell(@Header("Authorization") String accessToken, @Body CreateScheduleCellRequest createScheduleCellRequest);

    @PUT("admin/schedule-cell")
    Observable<ResponseBody> updateScheduleCell(@Header("Authorization") String accessToken, @Body UpdateScheduleCellRequest updateScheduleCellRequest);

}
