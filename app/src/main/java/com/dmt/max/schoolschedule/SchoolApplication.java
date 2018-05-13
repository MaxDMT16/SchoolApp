package com.dmt.max.schoolschedule;

import android.app.Application;
import android.os.StrictMode;

import com.dmt.max.schoolschedule.component.DaggerSchoolComponent;
import com.dmt.max.schoolschedule.component.SchoolComponent;
import com.dmt.max.schoolschedule.module.AppModule;
import com.dmt.max.schoolschedule.module.NetworkModule;
import com.dmt.max.schoolschedule.pupils.dagger.PupilsListingComponent;
import com.dmt.max.schoolschedule.pupils.dagger.PupilsListingModule;

/**
 * Created by Max on 29.04.2018.
 */

public class SchoolApplication extends Application {

    SchoolComponent schoolComponent;
    PupilsListingComponent pupilsListingComponent;

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

    public PupilsListingComponent createPupilsListingComponent(){
        pupilsListingComponent = schoolComponent.plus(new PupilsListingModule());
        return pupilsListingComponent;
    }

    public void releasePupilsListingComponent(){pupilsListingComponent = null;}

    public PupilsListingComponent getPupilsListingComponent(){return pupilsListingComponent;}
}
