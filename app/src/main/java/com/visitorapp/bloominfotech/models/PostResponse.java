package com.visitorapp.bloominfotech.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by prateekarora on 17/06/16.
 */
public class PostResponse {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("message")
    @Expose
    private String message = "";

    /**
     * @return The success
     */
    public boolean isSuccess() {
        return status;
    }

    /**
     * @param success The success
     */
    public void setSuccess(boolean success) {
        this.status = success;
    }

    /**
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
