package com.ekd.college.control.DBHandlers;

public class AdminModel {
    private String adminEmail;
    private String adminID;
    private String name;
    private String password;

    public AdminModel(String adminEmail, String adminID, String name, String password) {
        this.adminEmail = adminEmail;
        this.adminID = adminID;
        this.name = name;
        this.password = password;
    }

    public AdminModel() {

    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
