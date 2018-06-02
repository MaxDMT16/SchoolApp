package com.dmt.max.schoolschedule.teachers.dagger.details;

import com.dmt.max.schoolschedule.network.SchoolSystemWebService;
import com.dmt.max.schoolschedule.teachers.interactors.details.TeacherDetailsInteractor;
import com.dmt.max.schoolschedule.teachers.interactors.details.TeacherDetailsInteractorImpl;
import com.dmt.max.schoolschedule.teachers.presenters.details.TeacherDetailsPresenter;
import com.dmt.max.schoolschedule.teachers.presenters.details.TeacherDetailsPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Max on 02.06.2018.
 */

@Module
public class TeacherDetailsModule {
    @Provides
    TeacherDetailsInteractor provideTeacherDetailsInteractor(SchoolSystemWebService schoolSystemWebService){
        return new TeacherDetailsInteractorImpl(schoolSystemWebService);
    }

    @Provides
    TeacherDetailsPresenter provideTeacherDetailsPresenter(TeacherDetailsInteractor teacherDetailsInteractor){
        return new TeacherDetailsPresenterImpl(teacherDetailsInteractor);
    }
}
