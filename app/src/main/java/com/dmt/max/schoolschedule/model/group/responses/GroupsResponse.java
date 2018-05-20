package com.dmt.max.schoolschedule.model.group.responses;

/**
 * Created by Max on 20.05.2018.
 */
import java.util.List;

import com.dmt.max.schoolschedule.model.group.Group;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupsResponse {

    @SerializedName("groups")
    @Expose
    private List<Group> groups = null;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

}
