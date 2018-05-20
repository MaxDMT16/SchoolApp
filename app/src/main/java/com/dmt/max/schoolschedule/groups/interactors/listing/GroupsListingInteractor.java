package com.dmt.max.schoolschedule.groups.interactors.listing;

import com.dmt.max.schoolschedule.model.group.responses.GroupsResponse;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Max on 20.05.2018.
 */

public interface GroupsListingInteractor {
    Observable<ResponseBody> deleteGroup(String accessToken, String groupId);
    Observable<GroupsResponse> getGroups(String accessToken);
}
