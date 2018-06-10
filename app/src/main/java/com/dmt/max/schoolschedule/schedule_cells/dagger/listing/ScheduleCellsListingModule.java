package com.dmt.max.schoolschedule.schedule_cells.dagger.listing;

import com.dmt.max.schoolschedule.network.SchoolSystemWebService;
import com.dmt.max.schoolschedule.schedule_cells.interactors.listing.ScheduleCellsListingInteractor;
import com.dmt.max.schoolschedule.schedule_cells.interactors.listing.ScheduleCellsListingInteractorImpl;
import com.dmt.max.schoolschedule.schedule_cells.presenters.listing.ScheduleCellsListingPresenter;
import com.dmt.max.schoolschedule.schedule_cells.presenters.listing.ScheduleCellsListingPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Max on 09.06.2018.
 */

@Module
public class ScheduleCellsListingModule {
    @Provides
    public ScheduleCellsListingInteractor provideScheduleCellsListingInteractor(SchoolSystemWebService schoolSystemWebService){
        return new ScheduleCellsListingInteractorImpl(schoolSystemWebService);
    }

    @Provides
    public ScheduleCellsListingPresenter provideScheduleCellsListingPresenter(ScheduleCellsListingInteractor scheduleCellsListingInteractor){
        return new ScheduleCellsListingPresenterImpl(scheduleCellsListingInteractor);
    }
}
