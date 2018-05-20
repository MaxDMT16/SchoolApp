package com.dmt.max.schoolschedule;

import android.app.Application;

import com.dmt.max.schoolschedule.component.DaggerSchoolComponent;
import com.dmt.max.schoolschedule.component.SchoolComponent;
import com.dmt.max.schoolschedule.groups.dagger.listing.GroupsListingComponent;
import com.dmt.max.schoolschedule.groups.dagger.listing.GroupsListingModule;
import com.dmt.max.schoolschedule.module.AppModule;
import com.dmt.max.schoolschedule.module.NetworkModule;
import com.dmt.max.schoolschedule.pupils.dagger.details.PupilDetailsComponent;
import com.dmt.max.schoolschedule.pupils.dagger.details.PupilDetailsModule;
import com.dmt.max.schoolschedule.pupils.dagger.listing.PupilsListingComponent;
import com.dmt.max.schoolschedule.pupils.dagger.listing.PupilsListingModule;

/**
 * Created by Max on 29.04.2018.
 */

public class SchoolApplication extends Application {

    SchoolComponent schoolComponent;

    PupilsListingComponent pupilsListingComponent;
    PupilDetailsComponent pupilDetailsComponent;

    GroupsListingComponent groupsListingComponent;

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
}
