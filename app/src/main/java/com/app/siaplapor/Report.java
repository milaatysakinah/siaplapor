package com.app.siaplapor;

public class Report {
    private String nik;
    private String name;
    private String address;
    private String phone;
    private String report;
    private String date;

    public Report(String nik, String name, String address, String phone, String report, String date) {
        this.nik = nik;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.report = report;
        this.date = date;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
