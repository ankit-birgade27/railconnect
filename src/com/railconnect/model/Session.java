package com.railconnect.model;

import java.time.LocalDateTime;

public class Session {

    private String sessionId;

    private User user;

    private LocalDateTime loginTime;

    private LocalDateTime logoutTime;

    private boolean active;

    public Session() {
    }

    public Session(String sessionId,
                   User user,
                   LocalDateTime loginTime,
                   LocalDateTime logoutTime,
                   boolean active) {

        this.sessionId = sessionId;
        this.user = user;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.active = active;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public LocalDateTime getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(LocalDateTime logoutTime) {
        this.logoutTime = logoutTime;
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
                ", user=" + user +
                ", loginTime=" + loginTime +
                ", logoutTime=" + logoutTime +
                ", active=" + active +
                '}';
    }
}