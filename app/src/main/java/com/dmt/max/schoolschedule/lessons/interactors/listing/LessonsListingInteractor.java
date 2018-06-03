package com.dmt.max.schoolschedule.lessons.interactors.listing;

import com.dmt.max.schoolschedule.model.lesson.responses.LessonsResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Max on 03.06.2018.
 */

public interface LessonsListingInteractor {
    Observable<ResponseBody> deleteLesson(String accessToken, String lessonId);
    Observable<LessonsResponse> getLessons(String accessToken);
}
