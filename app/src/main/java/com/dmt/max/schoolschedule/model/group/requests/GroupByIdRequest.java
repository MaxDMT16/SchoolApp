package com.dmt.max.schoolschedule.model.group.requests;

/**
 * Created by Max on 28/05/2018.
 */

public class GroupByIdRequest {
    private String id;

    public GroupByIdRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
