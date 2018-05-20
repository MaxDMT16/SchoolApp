package com.dmt.max.schoolschedule.pupils.dagger.details;

import com.dmt.max.schoolschedule.module.AppModule;
import com.dmt.max.schoolschedule.pupils.views.details.PupilDetailsFragment;

import dagger.Subcomponent;

/**
 * Created by Max on 19.05.2018.
 */

@Subcomponent(modules = {PupilDetailsModule.class, AppModule.class})
public interface PupilDetailsComponent {
    PupilDetailsFragment inject(PupilDetailsFragment fragment);
}
