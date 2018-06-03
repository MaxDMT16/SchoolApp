package com.dmt.max.schoolschedule.lessons.interactors.listing;

import com.dmt.max.schoolschedule.model.lesson.responses.LessonsResponse;
import com.dmt.max.schoolschedule.network.SchoolSystemWebService;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Max on 03.06.2018.
 */

public class LessonsListingInteractorImpl implements LessonsListingInteractor {
    private SchoolSystemWebService schoolSystemWebService;

    public LessonsListingInteractorImpl(SchoolSystemWebService schoolSystemWebService) {
        this.schoolSystemWebService = schoolSystemWebService;
    }

    @Override
    public Observable<ResponseBody> deleteLesson(String accessToken, String lessonId) {
        return schoolSystemWebService.deleteLesson(accessToken, lessonId);
    }

    @Override
    public Observable<LessonsResponse> getLessons(String accessToken) {
        return schoolSystemWebService.getLessons(accessToken);
    }
}
