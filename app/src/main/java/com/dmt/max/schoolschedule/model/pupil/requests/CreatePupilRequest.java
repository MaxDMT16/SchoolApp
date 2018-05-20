package com.dmt.max.schoolschedule.model.pupil.requests;

/**
 * Created by Max on 19.05.2018.
 */

public class CreatePupilRequest {
    private String firstName;
    private String lastName;
    private String GroupId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGroupId() {
        return GroupId;
    }

    public void setGroupId(String groupId) {
        GroupId = groupId;
    }
}
