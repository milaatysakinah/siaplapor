package com.app.siaplapor.data.model;

public class Report {
    private String id;
    private String nik;
    private String name;
    private String phone;
    private String address;
    private String report;
    private String userId;

    public Report(String id, String nik, String name, String phone, String address, String report, String userId) {
        this.id = id;
        this.nik = nik;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.report = report;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
