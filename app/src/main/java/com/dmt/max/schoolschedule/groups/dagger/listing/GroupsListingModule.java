package com.dmt.max.schoolschedule.groups.dagger.listing;

import com.dmt.max.schoolschedule.groups.interactors.listing.GroupsListingInteractor;
import com.dmt.max.schoolschedule.groups.interactors.listing.GroupsListingInteractorImpl;
import com.dmt.max.schoolschedule.groups.presenters.listing.GroupsListingPresenter;
import com.dmt.max.schoolschedule.groups.presenters.listing.GroupsListingPresenterImpl;
import com.dmt.max.schoolschedule.network.SchoolSystemWebService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Max on 20.05.2018.
 */
@Module
public class GroupsListingModule {
    @Provides
    GroupsListingInteractor provideGroupsListingInteractor(SchoolSystemWebService schoolSystemWebService){
        return new GroupsListingInteractorImpl(schoolSystemWebService);
    }

    @Provides
    GroupsListingPresenter provideGroupsListingPresenter(GroupsListingInteractor groupsListingInteractor){
        return new GroupsListingPresenterImpl(groupsListingInteractor);
    }
}
