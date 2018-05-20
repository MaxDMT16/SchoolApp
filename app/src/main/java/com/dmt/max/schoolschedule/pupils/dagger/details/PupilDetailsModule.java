package com.dmt.max.schoolschedule.pupils.dagger.details;

import com.dmt.max.schoolschedule.network.SchoolSystemWebService;
import com.dmt.max.schoolschedule.pupils.interactors.details.PupilDetailsInteractor;
import com.dmt.max.schoolschedule.pupils.interactors.details.PupilDetailsInteractorImpl;
import com.dmt.max.schoolschedule.pupils.presenters.details.PupilDetailsPresenter;
import com.dmt.max.schoolschedule.pupils.presenters.details.PupilDetailsPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Max on 19.05.2018.
 */

@Module
public class PupilDetailsModule {

    @Provides
    PupilDetailsInteractor providePupilsListingInteractor(SchoolSystemWebService schoolSystemWebService) {
        return new PupilDetailsInteractorImpl(schoolSystemWebService);
    }

    @Provides
    PupilDetailsPresenter providePupilsListingPresenter(PupilDetailsInteractor pupilsListingInteractor) {
        return new PupilDetailsPresenterImpl(pupilsListingInteractor);
    }
}
