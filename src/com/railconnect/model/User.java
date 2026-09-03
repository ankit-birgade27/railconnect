package com.railconnect.model;



public class User {

    private int userId;
    private String username;
    private String email;
    private String mobile;
    private String password;
    private String role;
    private boolean accountLocked;
    private int loginAttempts;
    private String lastLogin;

    // Default Constructor
    public User() {
    }

    // Parameterized Constructor
    public User(int userId, String username, String email,
                String mobile, String password, String role,
                boolean accountLocked, int loginAttempts,
                String lastLogin) {

        this.userId = userId;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.role = role;
        this.accountLocked = accountLocked;
        this.loginAttempts = loginAttempts;
        this.lastLogin = lastLogin;
    }

    // Getters and Setters

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", role='" + role + '\'' +
                ", accountLocked=" + accountLocked +
                ", loginAttempts=" + loginAttempts +
                ", lastLogin='" + lastLogin + '\'' +
                '}';
    }
}