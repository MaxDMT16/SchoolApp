package com.dmt.max.schoolschedule;

import android.app.Application;

import com.dmt.max.schoolschedule.component.DaggerSchoolComponent;
import com.dmt.max.schoolschedule.component.SchoolComponent;
import com.dmt.max.schoolschedule.groups.dagger.details.GroupDetailsComponent;
import com.dmt.max.schoolschedule.groups.dagger.details.GroupDetailsModule;
import com.dmt.max.schoolschedule.groups.dagger.listing.GroupsListingComponent;
import com.dmt.max.schoolschedule.groups.dagger.listing.GroupsListingModule;
import com.dmt.max.schoolschedule.lessons.dagger.details.LessonDetailsComponent;
import com.dmt.max.schoolschedule.lessons.dagger.details.LessonDetailsModule;
import com.dmt.max.schoolschedule.lessons.dagger.listing.LessonsListingComponent;
import com.dmt.max.schoolschedule.lessons.dagger.listing.LessonsListingModule;
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

/**
 * Created by Max on 29.04.2018.
 */

public class SchoolApplication extends Application {

    SchoolComponent schoolComponent;

    PupilsListingComponent pupilsListingComponent;
    PupilDetailsComponent pupilDetailsComponent;

    GroupsListingComponent groupsListingComponent;
    GroupDetailsComponent groupDetailsComponent;

    TeachersListingComponent teachersListingComponent;
    TeacherDetailsComponent teacherDetailsComponent;

    LessonsListingComponent lessonsListingComponent;
    LessonDetailsComponent lessonDetailsComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        schoolComponent = createSchoolComponent();
    }

    private SchoolComponent createSchoolComponent() {
        return DaggerSchoolComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public SchoolComponent getSchoolComponent() {
        return schoolComponent;
    }

    public PupilsListingComponent createPupilsListingComponent() {
        pupilsListingComponent = schoolComponent.plus(new PupilsListingModule());
        return pupilsListingComponent;
    }

    public void releasePupilsListingComponent() {
        pupilsListingComponent = null;
    }

    public PupilsListingComponent getPupilsListingComponent() {
        return pupilsListingComponent;
    }


    public PupilDetailsComponent createPupilDetailsComponent() {
        pupilDetailsComponent = schoolComponent.plus(new PupilDetailsModule());
        return pupilDetailsComponent;
    }

    public void releasePupilDetailsComponent() {
        pupilDetailsComponent = null;
    }

    public PupilDetailsComponent getPupilDetailsComponent() {
        return pupilDetailsComponent;
    }


    public GroupsListingComponent createGroupsListingComponent() {
        groupsListingComponent = schoolComponent.plus(new GroupsListingModule());
        return groupsListingComponent;
    }

    public void releaseGroupsListingComponent() {
        groupsListingComponent = null;
    }


    public GroupDetailsComponent createGroupDetailsComponent() {
        groupDetailsComponent = schoolComponent.plus(new GroupDetailsModule());
        return groupDetailsComponent;
    }

    public void releaseGroupDetailsComponent() {
        groupDetailsComponent = null;
    }

    public TeacherDetailsComponent createTeacherDetailsComponent() {
        teacherDetailsComponent = schoolComponent.plus(new TeacherDetailsModule());
        return teacherDetailsComponent;
    }

    public void releaseTeacherDetialsComponent() {
        teacherDetailsComponent = null;
    }

    public TeachersListingComponent createTeachersListingComponent() {
        teachersListingComponent = schoolComponent.plus(new TeachersListingModule());
        return teachersListingComponent;
    }

    public void releaseTeachersListingComponent() {
        teachersListingComponent = null;
    }

    public LessonsListingComponent createLessonsListingComponent() {
        lessonsListingComponent = schoolComponent.plus(new LessonsListingModule());

        return lessonsListingComponent;
    }

    public void releaseLessonsListingComponent() {
        lessonsListingComponent = null;
    }

    public LessonDetailsComponent createLessonDetailsComponent() {
        lessonDetailsComponent = schoolComponent.plus(new LessonDetailsModule());

        return lessonDetailsComponent;
    }

    public void releaseLessonDetailsComponent() {
        lessonDetailsComponent = null;
    }
}
