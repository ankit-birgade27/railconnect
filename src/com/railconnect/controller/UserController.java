package com.railconnect.controller;

import com.railconnect.exception.DuplicateEmailException;
import com.railconnect.exception.DuplicateMobileException;
import com.railconnect.exception.DuplicateUsernameException;
import com.railconnect.exception.InvalidPasswordException;
import com.railconnect.exception.InvalidUserException;
import com.railconnect.model.User;
import com.railconnect.service.UserService;
import com.railconnect.serviceimpl.UserServiceImpl;

public class UserController {

    private UserService service;

    public UserController() {
        this.service = new UserServiceImpl();
    }

    public UserController(UserService service) {
        this.service = service;
    }

    public void getMsg(String msg) {
        String welcomemsg = service.welcome(msg);
        System.out.println(welcomemsg);
    }

    /**
     * Handles user registration request and provides user-facing feedback.
     */
    public boolean registerUser(User user) {
        try {
            service.registerUser(user);
            System.out.println("Registration Successful!");
            System.out.println("  User ID       : " + user.getUserId());
            System.out.println("  Username      : " + user.getUsername());
            System.out.println("  Email         : " + user.getEmail());
            System.out.println("  Mobile        : " + user.getMobile());
            System.out.println("  Role          : " + user.getRole());
            System.out.println("  AccountLocked : " + user.isAccountLocked());
            System.out.println("  LoginAttempts : " + user.getLoginAttempts());
            return true;
        } catch (DuplicateUsernameException e) {
            System.err.println("[Registration Error - Duplicate Username]: " + e.getMessage());
            return false;
        } catch (DuplicateEmailException e) {
            System.err.println("[Registration Error - Duplicate Email]: " + e.getMessage());
            return false;
        } catch (DuplicateMobileException e) {
            System.err.println("[Registration Error - Duplicate Mobile]: " + e.getMessage());
            return false;
        } catch (InvalidPasswordException e) {
            System.err.println("[Registration Error - Invalid Password]: " + e.getMessage());
            return false;
        } catch (InvalidUserException e) {
            System.err.println("[Registration Error - Invalid User Data]: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("[Registration Error - Unexpected]: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        UserController controller = new UserController();

        System.out.println("=================================================================");
        System.out.println("      RailConnect - Student 1: User Registration Demonstration     ");
        System.out.println("=================================================================\n");

        // 1. Successful Registration
        System.out.println("--- Test 1: Successful User Registration ---");
        User user1 = new User();
        user1.setUsername("ankit_b");
        user1.setEmail("ankit@gmail.com");
        user1.setMobile("9876543210");
        user1.setPassword("Secret@123");
        controller.registerUser(user1);
        System.out.println();

        // 2. Second Successful Registration (Unique User ID generation)
        System.out.println("--- Test 2: Second User Registration (Unique ID check) ---");
        User user2 = new User();
        user2.setUsername("rohit_sharma");
        user2.setEmail("rohit@railconnect.com");
        user2.setMobile("9876543211");
        user2.setPassword("RohitPass456");
        controller.registerUser(user2);
        System.out.println();

        // 3. Null User Check
        System.out.println("--- Test 3: Null User Validation ---");
        controller.registerUser(null);
        System.out.println();

        // 4. Invalid Username (< 3 characters)
        System.out.println("--- Test 4: Invalid Username (< 3 chars) ---");
        User invalidUsernameUser = new User();
        invalidUsernameUser.setUsername("ab");
        invalidUsernameUser.setEmail("valid@gmail.com");
        invalidUsernameUser.setMobile("9876543212");
        invalidUsernameUser.setPassword("password123");
        controller.registerUser(invalidUsernameUser);
        System.out.println();

        // 5. Invalid Email Format (e.g. ankitgmail.com)
        System.out.println("--- Test 5: Invalid Email Format (missing @ and domain) ---");
        User invalidEmailUser = new User();
        invalidEmailUser.setUsername("rahul_k");
        invalidEmailUser.setEmail("ankitgmail.com");
        invalidEmailUser.setMobile("9876543213");
        invalidEmailUser.setPassword("password123");
        controller.registerUser(invalidEmailUser);
        System.out.println();

        // 6. Invalid Mobile Number
        System.out.println("--- Test 6: Invalid Mobile Number (not 10 digits) ---");
        User invalidMobileUser = new User();
        invalidMobileUser.setUsername("suresh_p");
        invalidMobileUser.setEmail("suresh@gmail.com");
        invalidMobileUser.setMobile("98765");
        invalidMobileUser.setPassword("password123");
        controller.registerUser(invalidMobileUser);
        System.out.println();

        // 7. Invalid Password (< 6 chars)
        System.out.println("--- Test 7: Invalid Password (< 6 characters) ---");
        User invalidPasswordUser = new User();
        invalidPasswordUser.setUsername("priya_m");
        invalidPasswordUser.setEmail("priya@gmail.com");
        invalidPasswordUser.setMobile("9876543214");
        invalidPasswordUser.setPassword("123");
        controller.registerUser(invalidPasswordUser);
        System.out.println();

        // 8. Duplicate Username
        System.out.println("--- Test 8: Duplicate Username Check ---");
        User duplicateUsernameUser = new User();
        duplicateUsernameUser.setUsername("ankit_b");
        duplicateUsernameUser.setEmail("different@gmail.com");
        duplicateUsernameUser.setMobile("9876543215");
        duplicateUsernameUser.setPassword("password123");
        controller.registerUser(duplicateUsernameUser);
        System.out.println();

        // 9. Duplicate Email
        System.out.println("--- Test 9: Duplicate Email Check ---");
        User duplicateEmailUser = new User();
        duplicateEmailUser.setUsername("different_user");
        duplicateEmailUser.setEmail("ankit@gmail.com");
        duplicateEmailUser.setMobile("9876543216");
        duplicateEmailUser.setPassword("password123");
        controller.registerUser(duplicateEmailUser);
        System.out.println();

        // 10. Duplicate Mobile
        System.out.println("--- Test 10: Duplicate Mobile Check ---");
        User duplicateMobileUser = new User();
        duplicateMobileUser.setUsername("another_user");
        duplicateMobileUser.setEmail("another@gmail.com");
        duplicateMobileUser.setMobile("9876543210");
        duplicateMobileUser.setPassword("password123");
        controller.registerUser(duplicateMobileUser);
        System.out.println();

        System.out.println("=================================================================");
        System.out.println("                Demonstration Completed Successfully             ");
        System.out.println("=================================================================");
    }
}
