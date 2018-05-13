package com.dmt.max.schoolschedule.model.login;

/**
 * Created by Max on 01.05.2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FailLoginResponse {

    @SerializedName("TraceIdentifier")
    @Expose
    private String traceIdentifier;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Code")
    @Expose
    private String code;

    public String getTraceIdentifier() {
        return traceIdentifier;
    }

    public void setTraceIdentifier(String traceIdentifier) {
        this.traceIdentifier = traceIdentifier;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}