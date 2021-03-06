package com.app.siaplapor.response;

import com.app.siaplapor.model.Report;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponse {
    private Boolean status;
    private String message;

    @SerializedName("data")
    List<Report> list_report;

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

    public List<Report> getList_report() {
        return list_report;
    }

    public void setList_report(List<Report> report) {
        this.list_report = list_report;
    }
}
