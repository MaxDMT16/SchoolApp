package com.dmt.max.schoolschedule.component;

import com.dmt.max.schoolschedule.LoginActivity;
import com.dmt.max.schoolschedule.groups.dagger.details.GroupDetailsComponent;
import com.dmt.max.schoolschedule.groups.dagger.details.GroupDetailsModule;
import com.dmt.max.schoolschedule.groups.dagger.listing.GroupsListingComponent;
import com.dmt.max.schoolschedule.groups.dagger.listing.GroupsListingModule;
import com.dmt.max.schoolschedule.main.MainActivity;
import com.dmt.max.schoolschedule.module.AppModule;
import com.dmt.max.schoolschedule.module.NetworkModule;
import com.dmt.max.schoolschedule.pupils.dagger.details.PupilDetailsComponent;
import com.dmt.max.schoolschedule.pupils.dagger.details.PupilDetailsModule;
import com.dmt.max.schoolschedule.pupils.dagger.listing.PupilsListingComponent;
import com.dmt.max.schoolschedule.pupils.dagger.listing.PupilsListingModule;
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
}
