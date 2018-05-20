package com.dmt.max.schoolschedule.groups.dagger.listing;

import com.dmt.max.schoolschedule.groups.views.listing.GroupsListingFragment;
import com.dmt.max.schoolschedule.module.AppModule;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by Max on 20.05.2018.
 */
@Subcomponent(modules = {GroupsListingModule.class, AppModule.class})
public interface GroupsListingComponent {
    GroupsListingFragment inject(GroupsListingFragment groupsListingFragment);
}
