package com.visitorapp.bloominfotech.models;

/**
 * Created by hp on 11/8/2016.
 */

public class FinalReceiptModel {

    private String name = "";
    private String company = "";
    private String purpose = "";
    private String id = "";
    private String date = "";
    private String time = "";
    private String imagelink = "";

    public FinalReceiptModel(String name, String company, String purpose, String id, String date, String time, String imagelink) {
        this.name = name;
        this.company = company;
        this.purpose = purpose;
        this.id = id;
        this.date = date;
        this.time = time;
        this.imagelink = imagelink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }
}
