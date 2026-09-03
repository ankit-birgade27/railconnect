package com.railconnect.model;



public class Session {

    private String sessionId;
    private int userId;
    private String username;
    private String role;

    private String loginTime;
    private String lastActivityTime;

    private boolean active;

    // Default Constructor
    public Session() {
    }

    // Parameterized Constructor
    public Session(String sessionId, int userId,
                   String username, String role,
                   String loginTime,
                   String lastActivityTime,
                   boolean active) {

        this.sessionId = sessionId;
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.loginTime = loginTime;
        this.lastActivityTime = lastActivityTime;
        this.active = active;
    }

    // Getters and Setters

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLastActivityTime() {
        return lastActivityTime;
    }

    public void setLastActivityTime(String lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId='" + sessionId + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", lastActivityTime='" + lastActivityTime + '\'' +
                ", active=" + active +
                '}';
    }
}