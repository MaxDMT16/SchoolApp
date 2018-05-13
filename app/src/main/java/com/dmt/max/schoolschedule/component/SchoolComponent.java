package com.dmt.max.schoolschedule.component;

import com.dmt.max.schoolschedule.PupilsListingActivityTest;
import com.dmt.max.schoolschedule.LoginActivity;
import com.dmt.max.schoolschedule.main.MainActivity;
import com.dmt.max.schoolschedule.module.AppModule;
import com.dmt.max.schoolschedule.module.NetworkModule;
import com.dmt.max.schoolschedule.pupils.dagger.PupilsListingComponent;
import com.dmt.max.schoolschedule.pupils.dagger.PupilsListingModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Max on 29.04.2018.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface SchoolComponent {
    void inject(MainActivity schoolApplication);
    void inject(LoginActivity loginApplication);
    void inject(PupilsListingActivityTest pupilsListingActivityTest);

    PupilsListingComponent plus(PupilsListingModule pupilsListingModule);
}
