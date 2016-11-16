package com.visitorapp.bloominfotech.models.admin_login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 11/16/2016.
 */

public class ResponseAdminLogin {

    @SerializedName("isUserValid")
    @Expose
    private Boolean isUserValid;
    @SerializedName("UserRoles")
    @Expose
    private List<String> userRoles = new ArrayList<String>();
    @SerializedName("errorIfAny")
    @Expose
    private String errorIfAny;

    /**
     *
     * @return
     * The isUserValid
     */
    public Boolean getIsUserValid() {
        return isUserValid;
    }

    /**
     *
     * @param isUserValid
     * The isUserValid
     */
    public void setIsUserValid(Boolean isUserValid) {
        this.isUserValid = isUserValid;
    }

    /**
     *
     * @return
     * The userRoles
     */
    public List<String> getUserRoles() {
        return userRoles;
    }

    /**
     *
     * @param userRoles
     * The UserRoles
     */
    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }

    /**
     *
     * @return
     * The errorIfAny
     */
    public String getErrorIfAny() {
        return errorIfAny;
    }

    /**
     *
     * @param errorIfAny
     * The errorIfAny
     */
    public void setErrorIfAny(String errorIfAny) {
        this.errorIfAny = errorIfAny;
    }

}
