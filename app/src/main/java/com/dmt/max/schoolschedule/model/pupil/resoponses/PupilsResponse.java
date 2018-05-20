package com.dmt.max.schoolschedule.model.pupil.resoponses;

/**
 * Created by Max on 12.05.2018.
 */

import java.util.List;

import com.dmt.max.schoolschedule.model.pupil.Pupil;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PupilsResponse {

    @SerializedName("pupils")
    @Expose
    private List<Pupil> pupils = null;

    public List<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(List<Pupil> pupils) {
        this.pupils = pupils;
    }

}
