package com.dmt.max.schoolschedule.schedule_cells.dagger.listing;

import com.dmt.max.schoolschedule.module.AppModule;
import com.dmt.max.schoolschedule.schedule_cells.views.listing.ScheduleCellsListingFragment;

import dagger.Subcomponent;

/**
 * Created by Max on 09.06.2018.
 */

@Subcomponent(modules = {AppModule.class, ScheduleCellsListingModule.class})
public interface ScheduleCellsListingComponent {
    ScheduleCellsListingFragment inject(ScheduleCellsListingFragment scheduleCellsListingFragment);
}
