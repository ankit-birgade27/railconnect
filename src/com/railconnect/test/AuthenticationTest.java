package com.railconnect.test;

import com.railconnect.dao.AuthenticationDao;
import com.railconnect.dao.UserDao;
import com.railconnect.exception.AccountLockedException;
import com.railconnect.exception.InvalidCredentialsException;
import com.railconnect.exception.SessionNotFoundException;
import com.railconnect.exception.UserNotFoundException;
import com.railconnect.model.Session;
import com.railconnect.model.User;
import com.railconnect.serviceimpl.AuthenticationServiceImpl;
import com.railconnect.storage.DataStore;

public class AuthenticationTest {

    private AuthenticationDao authenticationDao;
    private UserDao userDao;
    private AuthenticationServiceImpl authenticationService;

    public static void main(String[] args) {

        AuthenticationTest test = new AuthenticationTest();

        test.testSuccessfulLogin();
        test.testUserNotFound();
        test.testInvalidPassword();
        test.testThreeFailedAttemptsLocksAccount();
        test.testLoginWhenAccountAlreadyLocked();
        test.testSuccessfulLoginResetsAttempts();
        test.testSessionCreatedAfterLogin();
        test.testSuccessfulLogout();
        test.testLogoutWithoutActiveSession();

        System.out.println("\nAll authentication tests completed.");
    }

    private void setUp() {

        userDao = new UserDao();
        authenticationDao = new AuthenticationDao();
        authenticationService = new AuthenticationServiceImpl();

        userDao.clear();
        DataStore.clearSessions();
    }

    private User createTestUser() {

        User user = new User();

        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setMobile("9876543210");
        user.setPassword("Test@123");
        user.setUserId(1001);
        user.setRole("PASSENGER");
        user.setAccountLocked(false);
        user.setLoginAttempts(0);

        userDao.saveUser(user);

        return user;
    }

    // ---------------------------------------------------------
    // Test 1: Successful Login
    // ---------------------------------------------------------

    public void testSuccessfulLogin() {

        setUp();

        User user = createTestUser();

        User loggedInUser =
                authenticationService.login("testuser", "Test@123");

        assertTrue(
                loggedInUser != null,
                "Successful login should return user"
        );

        assertEquals(
                user.getUserId(),
                loggedInUser.getUserId(),
                "Logged-in user ID should match"
        );

        System.out.println("PASS: testSuccessfulLogin");
    }

    // ---------------------------------------------------------
    // Test 2: User Not Found
    // ---------------------------------------------------------

    public void testUserNotFound() {

        setUp();

        try {

            authenticationService.login(
                    "unknownuser",
                    "Test@123"
            );

            fail("UserNotFoundException was expected");

        } catch (UserNotFoundException e) {

            System.out.println("PASS: testUserNotFound");
        }
    }

    // ---------------------------------------------------------
    // Test 3: Invalid Password
    // ---------------------------------------------------------

    public void testInvalidPassword() {

        setUp();

        createTestUser();

        try {

            authenticationService.login(
                    "testuser",
                    "Wrong@123"
            );

            fail("InvalidCredentialsException was expected");

        } catch (InvalidCredentialsException e) {

            User user =
                    authenticationDao.findByUsername("testuser");

            assertEquals(
                    1,
                    user.getLoginAttempts(),
                    "Login attempts should become 1"
            );

            System.out.println("PASS: testInvalidPassword");
        }
    }

    // ---------------------------------------------------------
    // Test 4: Three Failed Attempts Lock Account
    // ---------------------------------------------------------

    public void testThreeFailedAttemptsLocksAccount() {

        setUp();

        createTestUser();

        for (int i = 1; i <= 2; i++) {

            try {

                authenticationService.login(
                        "testuser",
                        "Wrong@123"
                );

            } catch (InvalidCredentialsException e) {
                // Expected
            }
        }

        try {

            authenticationService.login(
                    "testuser",
                    "Wrong@123"
            );

            fail("AccountLockedException was expected");

        } catch (AccountLockedException e) {

            User user =
                    authenticationDao.findByUsername("testuser");

            assertEquals(
                    3,
                    user.getLoginAttempts(),
                    "Login attempts should be 3"
            );

            assertTrue(
                    user.isAccountLocked(),
                    "Account should be locked after 3 failures"
            );

            System.out.println(
                    "PASS: testThreeFailedAttemptsLocksAccount"
            );
        }
    }

    // ---------------------------------------------------------
    // Test 5: Login When Account Already Locked
    // ---------------------------------------------------------

    public void testLoginWhenAccountAlreadyLocked() {

        setUp();

        User user = createTestUser();

        user.setAccountLocked(true);

        try {

            authenticationService.login(
                    "testuser",
                    "Test@123"
            );

            fail("AccountLockedException was expected");

        } catch (AccountLockedException e) {

            System.out.println(
                    "PASS: testLoginWhenAccountAlreadyLocked"
            );
        }
    }

    // ---------------------------------------------------------
    // Test 6: Successful Login Resets Attempts
    // ---------------------------------------------------------

    public void testSuccessfulLoginResetsAttempts() {

        setUp();

        User user = createTestUser();

        user.setLoginAttempts(2);

        authenticationService.login(
                "testuser",
                "Test@123"
        );

        assertEquals(
                0,
                user.getLoginAttempts(),
                "Successful login should reset attempts to 0"
        );

        System.out.println(
                "PASS: testSuccessfulLoginResetsAttempts"
        );
    }

    // ---------------------------------------------------------
    // Test 7: Session Created After Login
    // ---------------------------------------------------------

    public void testSessionCreatedAfterLogin() {

        setUp();

        User user = createTestUser();

        authenticationService.login(
                "testuser",
                "Test@123"
        );

        Session session =
                authenticationDao.findActiveSessionByUserId(
                        user.getUserId()
                );

        assertTrue(
                session != null,
                "Active session should be created"
        );

        assertTrue(
                session.isActive(),
                "Session should be active"
        );

        assertTrue(
                session.getLoginTime() != null,
                "Login time should be set"
        );

        assertTrue(
                session.getLogoutTime() == null,
                "Logout time should initially be null"
        );

        System.out.println(
                "PASS: testSessionCreatedAfterLogin"
        );
    }

    // ---------------------------------------------------------
    // Test 8: Successful Logout
    // ---------------------------------------------------------

    public void testSuccessfulLogout() {

        setUp();

        User user = createTestUser();

        authenticationService.login(
                "testuser",
                "Test@123"
        );

        authenticationService.logout(user.getUserId());

        Session session =
                authenticationDao.findActiveSessionByUserId(
                        user.getUserId()
                );

        assertTrue(
                session == null,
                "There should be no active session after logout"
        );

        System.out.println("PASS: testSuccessfulLogout");
    }

    // ---------------------------------------------------------
    // Test 9: Logout Without Active Session
    // ---------------------------------------------------------

    public void testLogoutWithoutActiveSession() {

        setUp();

        User user = createTestUser();

        try {

            authenticationService.logout(user.getUserId());

            fail("SessionNotFoundException was expected");

        } catch (SessionNotFoundException e) {

            System.out.println(
                    "PASS: testLogoutWithoutActiveSession"
            );
        }
    }

    // ---------------------------------------------------------
    // Assertion Methods
    // ---------------------------------------------------------

    private void assertTrue(
            boolean condition,
            String message) {

        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private void assertEquals(
            int expected,
            int actual,
            String message) {

        if (expected != actual) {

            throw new AssertionError(
                    message +
                    " | Expected: " +
                    expected +
                    " | Actual: " +
                    actual
            );
        }
    }

    private void fail(String message) {

        throw new AssertionError(message);
    }
}