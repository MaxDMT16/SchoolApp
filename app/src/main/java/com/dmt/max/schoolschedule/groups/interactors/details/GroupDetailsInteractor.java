package com.dmt.max.schoolschedule.groups.interactors.details;

import com.dmt.max.schoolschedule.model.group.requests.CreateGroupRequest;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by Max on 20.05.2018.
 */

public interface GroupDetailsInteractor {
    Observable<ResponseBody> createGroup(String accessToken, CreateGroupRequest createGroupRequest);
}
