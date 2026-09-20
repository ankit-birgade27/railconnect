package com.railconnect.dao;

import java.util.ArrayList;
import java.util.List;

import com.railconnect.model.Train;
import com.railconnect.model.User;

public class AdminDao {

    private List<User> users = new ArrayList<>();

    private List<Train> trains = new ArrayList<>();

        public void saveUser(User user) {

            users.add(user);
        }

        public User findByUsername(String username) {

            for (int i = 0; i < users.size(); i++) {

                User user = users.get(i);

                if (user.getUsername().equals(username)) {

                    return user;
                }
            }

            return null;
        }

        public User findByEmail(String email) {

            for (int i = 0; i < users.size(); i++) {

                User user = users.get(i);

                if (user.getEmail().equals(email)) {

                    return user;
                }
            }

            return null;
        }

        public User findByMobile(String mobile) {

            for (int i = 0; i < users.size(); i++) {

                User user = users.get(i);

                if (user.getMobile().equals(mobile)) {

                    return user;
                }
            }

            return null;
        }

        public boolean findbyUserId(int userId) {

            for (int i = 0; i < users.size(); i++) {

                User user = users.get(i);

                if (user.getUserId() == userId) {

                    return true;
                }
            }

            return false;
        }

        public int generateUniqueUserId() {

            int userId = 1001;

            while (findbyUserId(userId)) {

                userId++;
            }

            return userId;
        }
    }