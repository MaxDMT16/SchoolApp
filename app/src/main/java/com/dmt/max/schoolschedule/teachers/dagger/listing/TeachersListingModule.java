package com.dmt.max.schoolschedule.teachers.dagger.listing;

import com.dmt.max.schoolschedule.network.SchoolSystemWebService;
import com.dmt.max.schoolschedule.teachers.interactors.listing.TeachersListingInteractor;
import com.dmt.max.schoolschedule.teachers.interactors.listing.TeachersListingInteractorImpl;
import com.dmt.max.schoolschedule.teachers.presenters.listing.TeachersListingPresenter;
import com.dmt.max.schoolschedule.teachers.presenters.listing.TeachersListingPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Max on 02.06.2018.
 */

@Module
public class TeachersListingModule {
    @Provides
    TeachersListingInteractor provideTeachersListingInteractor(SchoolSystemWebService schoolSystemWebService){
        return new TeachersListingInteractorImpl(schoolSystemWebService);
    }

    @Provides
    TeachersListingPresenter provideTeachersListingPresenter(TeachersListingInteractor teachersListingInteractor){
        return new TeachersListingPresenterImpl(teachersListingInteractor);
    }
}
