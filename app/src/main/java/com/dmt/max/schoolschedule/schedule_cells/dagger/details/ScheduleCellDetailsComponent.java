package com.dmt.max.schoolschedule.schedule_cells.dagger.details;

import com.dmt.max.schoolschedule.module.AppModule;
import com.dmt.max.schoolschedule.schedule_cells.views.details.ScheduleCellDetailsFragment;

import dagger.Subcomponent;

/**
 * Created by Max on 09.06.2018.
 */
@Subcomponent(modules = {AppModule.class, ScheduleCellDetailsModule.class})
public interface ScheduleCellDetailsComponent {
    ScheduleCellDetailsFragment inject(ScheduleCellDetailsFragment scheduleCellDetailsFragment);
}
