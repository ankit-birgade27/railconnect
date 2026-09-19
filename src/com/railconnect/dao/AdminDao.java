package com.railconnect.dao;

import java.util.ArrayList;
import java.util.List;

import com.railconnect.model.User;

public class AdminDao {

    private List<User> users = new ArrayList<>();

    public User findById(int userId) {

        for (User user : users) {

            if (user != null && user.getUserId() == userId) {
                return user;
            }
        }

        return null;
    }

    public User findByUsername(String username) {

        for (User user : users) {

            if (user != null && 
                user.getUsername().equals(username)) {

                return user;
            }
        }

        return null;
    }

    public User findByEmail(String email) {

        for (User user : users) {

            if (user != null && 
                user.getEmail().equals(email)) {

                return user;
            }
        }

        return null;
    }

    public User findByMobile(String mobile) {

        for (User user : users) {

            if (user != null && 
                user.getMobile().equals(mobile)) {

                return user;
            }
        }

        return null;
    }

    public void updateUser(User user) {

        for (int i = 0; i < users.size(); i++) {

            User existingUser = users.get(i);

            if (existingUser != null &&
                existingUser.getUserId() == user.getUserId()) {

                users.set(i, user);
                return;
            }
        }
    }
}

