package com.dmt.max.schoolschedule.lessons.dagger.details;

import com.dmt.max.schoolschedule.lessons.views.details.LessonDetailsFragment;
import com.dmt.max.schoolschedule.module.AppModule;

import dagger.Subcomponent;

/**
 * Created by Max on 03.06.2018.
 */

@Subcomponent(modules = {AppModule.class, LessonDetailsModule.class})
public interface LessonDetailsComponent {
    LessonDetailsFragment inject(LessonDetailsFragment lessonDetailsFragment);
}
