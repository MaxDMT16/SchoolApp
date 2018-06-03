package com.dmt.max.schoolschedule.lessons.dagger.listing;

import com.dmt.max.schoolschedule.lessons.views.listing.LessonsListingFragment;
import com.dmt.max.schoolschedule.module.AppModule;

import dagger.Subcomponent;

/**
 * Created by Max on 03.06.2018.
 */

@Subcomponent(modules = {AppModule.class, LessonsListingModule.class})
public interface LessonsListingComponent {
    LessonsListingFragment inject(LessonsListingFragment lessonsListingFragment);
}
