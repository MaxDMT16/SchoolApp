package com.dmt.max.schoolschedule.teachers.dagger.listing;

import com.dmt.max.schoolschedule.teachers.views.listing.TeachersListingFragment;

import dagger.Subcomponent;

/**
 * Created by Max on 02.06.2018.
 */

@Subcomponent
public interface TeachersListingComponent {
    TeachersListingFragment inject(TeachersListingFragment teachersListingFragment);
}
