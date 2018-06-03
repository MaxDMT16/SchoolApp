package com.dmt.max.schoolschedule.lessons.dagger.details;

import com.dmt.max.schoolschedule.lessons.interactors.details.LessonDetailsInteractor;
import com.dmt.max.schoolschedule.lessons.interactors.details.LessonDetailsInteractorImpl;
import com.dmt.max.schoolschedule.lessons.presenters.details.LessonDetailsPresenter;
import com.dmt.max.schoolschedule.lessons.presenters.details.LessonDetailsPresenterImpl;
import com.dmt.max.schoolschedule.network.SchoolSystemWebService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Max on 03.06.2018.
 */

@Module
public class LessonDetailsModule {
    @Provides
    public LessonDetailsInteractor provideLessonDetailsInteractor(SchoolSystemWebService schoolSystemWebService){
        return new LessonDetailsInteractorImpl(schoolSystemWebService);
    }

    @Provides
    public LessonDetailsPresenter provideLessonDetailsPresenter(LessonDetailsInteractor lessonDetailsInteractor){
        return new LessonDetailsPresenterImpl(lessonDetailsInteractor);
    }
}
