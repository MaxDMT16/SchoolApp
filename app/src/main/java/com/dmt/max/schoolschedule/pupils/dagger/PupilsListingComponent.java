package com.dmt.max.schoolschedule.pupils.dagger;

import com.dmt.max.schoolschedule.PupilsListingActivityTest;
import com.dmt.max.schoolschedule.module.AppModule;
import com.dmt.max.schoolschedule.pupils.views.PupilsListingFragment;

import dagger.Subcomponent;

/**
 * Created by Max on 13.05.2018.
 */

@Subcomponent(modules = {PupilsListingModule.class, AppModule.class})
public interface PupilsListingComponent {
    PupilsListingFragment inject(PupilsListingFragment fragment);
}
