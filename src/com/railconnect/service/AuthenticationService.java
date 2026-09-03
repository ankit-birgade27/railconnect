package com.railconnect.service;

import com.railconnect.model.User;

public interface AuthenticationService {

    User login(String username, String password);

    void logout(int userId);

    boolean validateUsername(String username);

    boolean validateEmail(String email);

    boolean validateMobile(String mobile);

    boolean validatePassword(String password);

    boolean isAccountLocked(int userId);

    int getLoginAttempts(int userId);
}