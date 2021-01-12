package com.app.siaplapor.response;

import com.app.siaplapor.model.Report;
import com.app.siaplapor.model.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    private Boolean status;
    private String message;

    @SerializedName("data")
    List<User> list_user;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getList_user() {
        return list_user;
    }

    public void setList_user (List<User> user) {
        this.list_user = list_user;
    }
}
