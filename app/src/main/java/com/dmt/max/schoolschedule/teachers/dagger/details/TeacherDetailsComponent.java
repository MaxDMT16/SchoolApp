package com.dmt.max.schoolschedule.teachers.dagger.details;

import com.dmt.max.schoolschedule.module.AppModule;
import com.dmt.max.schoolschedule.teachers.views.details.TeacherDetailsFragment;

import dagger.Subcomponent;

/**
 * Created by Max on 02.06.2018.
 */

@Subcomponent(modules = {AppModule.class, TeacherDetailsModule.class})
public interface TeacherDetailsComponent {
    TeacherDetailsFragment inject(TeacherDetailsFragment teacherDetailsFragment);
}
