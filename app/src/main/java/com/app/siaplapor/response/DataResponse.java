package com.app.siaplapor.response;

import com.app.siaplapor.model.LaporanModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponse {
    private Boolean status;
    private String message;

    @SerializedName("data")
    List<LaporanModel> list_laporan;

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

    public List<LaporanModel> getList_laporan() {
        return list_laporan;
    }

    public void setList_laporan(List<LaporanModel> list_laporan) {
        this.list_laporan = list_laporan;
    }
}
