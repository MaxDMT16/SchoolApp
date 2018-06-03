package com.dmt.max.schoolschedule.lessons.dagger.listing;

import com.dmt.max.schoolschedule.lessons.interactors.listing.LessonsListingInteractor;
import com.dmt.max.schoolschedule.lessons.interactors.listing.LessonsListingInteractorImpl;
import com.dmt.max.schoolschedule.lessons.presenters.listing.LessonsListingPresenter;
import com.dmt.max.schoolschedule.lessons.presenters.listing.LessonsListingPresenterImpl;
import com.dmt.max.schoolschedule.network.SchoolSystemWebService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Max on 03.06.2018.
 */

@Module
public class LessonsListingModule {
    @Provides
    public LessonsListingInteractor provideLessonsListingInteractor(SchoolSystemWebService schoolSystemWebService){
        return new LessonsListingInteractorImpl(schoolSystemWebService);
    }

    @Provides
    public LessonsListingPresenter provideLessonsListingPresenter(LessonsListingInteractor lessonsListingInteractor){
        return new LessonsListingPresenterImpl(lessonsListingInteractor);
    }
}
