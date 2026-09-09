package com.railconnect.controller.dao;

import java.util.List;
import com.railconnect.model.User;

public interface UserDao {

    void saveUser(User user);

    User findByUsername(String username);

    User findByEmail(String email);

    User findByMobile(String mobile);

    User findById(int userId);

    List<User> findAllUsers();

    int generateUniqueUserId();

    String getnewMessage(String msg);

    void clear();
}
