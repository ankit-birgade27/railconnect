package com.railconnect.controller.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.railconnect.model.User;

public class UserDaoImpl implements UserDao {

    private static final List<User> userDatabase = new ArrayList<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(1001);

    @Override
    public synchronized void saveUser(User user) {
        if (user == null) {
            return;
        }
        for (int i = 0; i < userDatabase.size(); i++) {
            if (userDatabase.get(i).getUserId() == user.getUserId()) {
                userDatabase.set(i, user);
                return;
            }
        }
        userDatabase.add(user);
    }

    @Override
    public synchronized User findByUsername(String username) {
        if (username == null) {
            return null;
        }
        String trimmed = username.trim();
        for (User u : userDatabase) {
            if (u.getUsername() != null && u.getUsername().equalsIgnoreCase(trimmed)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public synchronized User findByEmail(String email) {
        if (email == null) {
            return null;
        }
        String trimmed = email.trim();
        for (User u : userDatabase) {
            if (u.getEmail() != null && u.getEmail().equalsIgnoreCase(trimmed)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public synchronized User findByMobile(String mobile) {
        if (mobile == null) {
            return null;
        }
        String trimmed = mobile.trim();
        for (User u : userDatabase) {
            if (u.getMobile() != null && u.getMobile().trim().equals(trimmed)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public synchronized User findById(int userId) {
        for (User u : userDatabase) {
            if (u.getUserId() == userId) {
                return u;
            }
        }
        return null;
    }

    @Override
    public synchronized List<User> findAllUsers() {
        return Collections.unmodifiableList(new ArrayList<>(userDatabase));
    }

    @Override
    public int generateUniqueUserId() {
        return idGenerator.getAndIncrement();
    }

    @Override
    public String getnewMessage(String msg) {
        return msg;
    }

    @Override
    public synchronized void clear() {
        userDatabase.clear();
        idGenerator.set(1001);
    }
}
