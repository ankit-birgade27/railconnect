package com.railconnect.dao;
import java.util.ArrayList;
<<<<<<< Updated upstream
=======
//import java.util.Collections;
>>>>>>> Stashed changes
import java.util.List;

import com.railconnect.model.User;
import com.railconnect.storage.DataStore;

public class UserDao {
<<<<<<< Updated upstream
	public void save(User user) {
		DataStore.getUsers().add(user);
	}
	
	public User findById(int userId) {
		List<User> users = DataStore.getUsers();
		for(User user : users) {
=======

//    private static final List<User> users = new ArrayList<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(1001);

//    public void registerUser(User user) {
//    		DataStore.getUsers().add(user);
//          saveUser(user);
//    }

    public synchronized void saveUser(User user) {
//        if (user == null) {
//            return;
//        }
//        for (int i = 0; i < users.size(); i++) {
//            if (users.get(i).getUserId() == user.getUserId()) {
//                users.set(i, user);
//                return;
//            }
//        }
//        users.add(user);
    	
    		DataStore.getUsers().add(user);
    }

    public synchronized User findByUsername(String username) {
//        if (username == null) {
//            return null;
//        }
////        String trimmed = username.trim();
//        for (User u : users) {
//            if (u.getUsername() != null && u.getUsername().equalsIgnoreCase(trimmed)) {
//                return u;
//            }
//        }
//        return null;
    	
    		List<User> users = DataStore.getUsers();
    		for(User user: users) {
    			if(username != null && username.equalsIgnoreCase(user.getUsername())) {
    				return user;
    			}
    		}
    		
    		return null;
    }

    public synchronized User findByEmail(String email) {
//        if (email == null) {
//            return null;
//        }
//        String trimmed = email.trim();
//        for (User u : users) {
//            if (u.getEmail() != null && u.getEmail().equalsIgnoreCase(trimmed)) {
//                return u;
//            }
//        }
//        return null;
    	
    		List<User> users = DataStore.getUsers();
    		for(User user: users) {
    			if(email != null && email.equalsIgnoreCase(user.getEmail())) {
    				return user;
    			}
    		}
    		
    		return null;
    }

    public synchronized User findByMobile(String mobile) {
//        if (mobile == null) {
//            return null;
//        }
//        String trimmed = mobile.trim();
//        for (User u : users) {
//            if (u.getMobile() != null && u.getMobile().trim().equals(trimmed)) {
//                return u;
//            }
//        }
//        return null;
    		List<User> users = DataStore.getUsers();
    		for(User user: users) {
    			if(mobile != null && mobile.equalsIgnoreCase(user.getMobile())) {
    				return user;
    			}
    		}
    		
    		return null;
    }

    public synchronized User findById(int userId) {
//        for (User u : users) {
//            if (u.getUserId() == userId) {
//                return u;
//            }
//        }
//        return null;
    		List<User> users = DataStore.getUsers();
		for(User user: users) {
>>>>>>> Stashed changes
			if(user.getUserId() == userId) {
				return user;
			}
		}
		
		return null;
<<<<<<< Updated upstream
	}
	
	
	public User findByUsername(String username) {
		List<User> users = DataStore.getUsers();
		for(User user: users) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		
		return null;
	}
	
	public User findByEmail(String email) {
		List<User> users = DataStore.getUsers();
		for(User user: users) {
			if(user.getEmail().equals(email)) {
				return user;
			}
		}
		
		return null;
	}
	
	public User findByMobile(String mobile) {
		List<User> users = DataStore.getUsers();
		for(User user: users) {
			if(user.getMobile().equals(mobile)) {
				return user;
			}
		}
		
		return null;
	}
	
	public List<User> findAll(){
		return new ArrayList<>(DataStore.getUsers());
	}
=======
    }

    public synchronized List<User> findAllUsers() {
//        return Collections.unmodifiableList(new ArrayList<>(users));
    		return new ArrayList<>(DataStore.getUsers());
    }

    public int generateUniqueUserId() {
        return idGenerator.getAndIncrement();
    }
//
//    public String getnewMessage(String msg) {
//        return msg;
//    }
//
//    public synchronized void clear() {
//        users.clear();
//        idGenerator.set(1001);
//    }
>>>>>>> Stashed changes
}
