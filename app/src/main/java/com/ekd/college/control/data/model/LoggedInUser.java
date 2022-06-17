package com.ekd.college.control.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {
    private String userId;
    private String displayName;


    public LoggedInUser(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }


    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
