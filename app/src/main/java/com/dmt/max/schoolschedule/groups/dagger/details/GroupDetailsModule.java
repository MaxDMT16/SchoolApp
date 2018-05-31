package com.dmt.max.schoolschedule.groups.dagger.details;

import com.dmt.max.schoolschedule.groups.interactors.details.GroupDetailsInteractor;
import com.dmt.max.schoolschedule.groups.interactors.details.GroupDetailsInteractorImpl;
import com.dmt.max.schoolschedule.groups.presenters.details.GroupDetailsPresenter;
import com.dmt.max.schoolschedule.groups.presenters.details.GroupDetailsPresenterImpl;
import com.dmt.max.schoolschedule.network.SchoolSystemWebService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Max on 20.05.2018.
 */

@Module
public class GroupDetailsModule {
    @Provides
    GroupDetailsInteractor provideInteractor(SchoolSystemWebService schoolSystemWebService) {
        return new GroupDetailsInteractorImpl(schoolSystemWebService);
    }

    @Provides
    GroupDetailsPresenter providePresenter(GroupDetailsInteractor groupDetailsInteractor) {
        return new GroupDetailsPresenterImpl(groupDetailsInteractor);
    }
}
