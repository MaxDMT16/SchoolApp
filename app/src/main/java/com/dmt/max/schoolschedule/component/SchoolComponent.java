package com.dmt.max.schoolschedule.component;

import com.dmt.max.schoolschedule.LoginActivity;
import com.dmt.max.schoolschedule.groups.dagger.details.GroupDetailsComponent;
import com.dmt.max.schoolschedule.groups.dagger.details.GroupDetailsModule;
import com.dmt.max.schoolschedule.groups.dagger.listing.GroupsListingComponent;
import com.dmt.max.schoolschedule.groups.dagger.listing.GroupsListingModule;
import com.dmt.max.schoolschedule.lessons.dagger.details.LessonDetailsComponent;
import com.dmt.max.schoolschedule.lessons.dagger.details.LessonDetailsModule;
import com.dmt.max.schoolschedule.lessons.dagger.listing.LessonsListingComponent;
import com.dmt.max.schoolschedule.lessons.dagger.listing.LessonsListingModule;
import com.dmt.max.schoolschedule.main.MainActivity;
import com.dmt.max.schoolschedule.module.AppModule;
import com.dmt.max.schoolschedule.module.NetworkModule;
import com.dmt.max.schoolschedule.pupils.dagger.details.PupilDetailsComponent;
import com.dmt.max.schoolschedule.pupils.dagger.details.PupilDetailsModule;
import com.dmt.max.schoolschedule.pupils.dagger.listing.PupilsListingComponent;
import com.dmt.max.schoolschedule.pupils.dagger.listing.PupilsListingModule;
import com.dmt.max.schoolschedule.schedule_cells.dagger.details.ScheduleCellDetailsComponent;
import com.dmt.max.schoolschedule.schedule_cells.dagger.details.ScheduleCellDetailsModule;
import com.dmt.max.schoolschedule.schedule_cells.dagger.listing.ScheduleCellsListingComponent;
import com.dmt.max.schoolschedule.schedule_cells.dagger.listing.ScheduleCellsListingModule;
import com.dmt.max.schoolschedule.teachers.dagger.details.TeacherDetailsComponent;
import com.dmt.max.schoolschedule.teachers.dagger.details.TeacherDetailsModule;
import com.dmt.max.schoolschedule.teachers.dagger.listing.TeachersListingComponent;
import com.dmt.max.schoolschedule.teachers.dagger.listing.TeachersListingModule;

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

    PupilsListingComponent plus(PupilsListingModule pupilsListingModule);

    PupilDetailsComponent plus(PupilDetailsModule pupilsListingModule);

    GroupsListingComponent plus(GroupsListingModule groupsListingModule);

    GroupDetailsComponent plus(GroupDetailsModule groupsListingModule);

    TeachersListingComponent plus(TeachersListingModule teachersListingModule);

    TeacherDetailsComponent plus(TeacherDetailsModule teacherDetailsModule);

    LessonsListingComponent plus(LessonsListingModule lessonsListingModule);

    LessonDetailsComponent plus(LessonDetailsModule lessonDetailsModule);

    ScheduleCellsListingComponent plus(ScheduleCellsListingModule scheduleCellsListingModule);

    ScheduleCellDetailsComponent plus(ScheduleCellDetailsModule scheduleCellDetailsModule);
}
