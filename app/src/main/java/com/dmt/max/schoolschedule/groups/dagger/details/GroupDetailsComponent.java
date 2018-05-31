package com.dmt.max.schoolschedule.groups.dagger.details;

import com.dmt.max.schoolschedule.groups.views.details.GroupDetailsFragment;
import com.dmt.max.schoolschedule.module.AppModule;

import dagger.Subcomponent;

/**
 * Created by Max on 20.05.2018.
 */

@Subcomponent(modules = {GroupDetailsModule.class, AppModule.class})
public interface GroupDetailsComponent {
    GroupDetailsFragment inject(GroupDetailsFragment groupDetailsFragment);
}
