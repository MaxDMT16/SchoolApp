package com.dmt.max.schoolschedule.pupils.dagger.listing;

import com.dmt.max.schoolschedule.network.SchoolSystemWebService;
import com.dmt.max.schoolschedule.pupils.interactors.listing.PupilsListingInteractor;
import com.dmt.max.schoolschedule.pupils.interactors.listing.PupilsListingInteractorImpl;
import com.dmt.max.schoolschedule.pupils.presenters.listing.PupilsListingPresenter;
import com.dmt.max.schoolschedule.pupils.presenters.listing.PupilsListingPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Max on 13.05.2018.
 */
@Module
public class PupilsListingModule {

    @Provides
    PupilsListingInteractor providePupilsListingInteractor(SchoolSystemWebService schoolSystemWebService) {
        return new PupilsListingInteractorImpl(schoolSystemWebService);
    }

    @Provides
    PupilsListingPresenter providePupilsListingPresenter(PupilsListingInteractor pupilsListingInteractor) {
        return new PupilsListingPresenterImpl(pupilsListingInteractor);
    }
}
