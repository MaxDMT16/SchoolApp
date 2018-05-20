package com.dmt.max.schoolschedule.model.pupil.requests;

/**
 * Created by Max on 20.05.2018.
 */

public class PupilByIdRequest {
    private String pupilId;

    public PupilByIdRequest(String pupilId) {
        this.pupilId = pupilId;
    }

    public String getPupilId() {
        return pupilId;
    }
}
