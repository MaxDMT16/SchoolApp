package com.dmt.max.schoolschedule.schedule_cells.dagger.details;

import com.dmt.max.schoolschedule.network.SchoolSystemWebService;
import com.dmt.max.schoolschedule.schedule_cells.interactors.details.ScheduleCellDetailsInteractor;
import com.dmt.max.schoolschedule.schedule_cells.interactors.details.ScheduleCellDetailsInteractorImpl;
import com.dmt.max.schoolschedule.schedule_cells.presenters.details.ScheduleCellDetailsPresenter;
import com.dmt.max.schoolschedule.schedule_cells.presenters.details.ScheduleCellDetailsPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Max on 09.06.2018.
 */

@Module
public class ScheduleCellDetailsModule {
    @Provides
    public ScheduleCellDetailsInteractor provideScheduleCellDetailsInteractor(SchoolSystemWebService schoolSystemWebService){
        return new ScheduleCellDetailsInteractorImpl(schoolSystemWebService);
    }

    @Provides
    public ScheduleCellDetailsPresenter provideScheduleCellDetailsPresenter(ScheduleCellDetailsInteractor scheduleCellDetailsInteractor){
        return new ScheduleCellDetailsPresenterImpl(scheduleCellDetailsInteractor);
    }
}
