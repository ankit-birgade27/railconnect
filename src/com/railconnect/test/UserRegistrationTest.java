package com.railconnect.test;

import com.railconnect.dao.UserDao;
import com.railconnect.exception.DuplicateEmailException;
import com.railconnect.exception.DuplicateMobileException;
import com.railconnect.exception.DuplicateUsernameException;
import com.railconnect.exception.InvalidPasswordException;
import com.railconnect.exception.InvalidUserException;
import com.railconnect.model.User;
import com.railconnect.service.UserService;
import com.railconnect.serviceimpl.UserServiceImpl;

public class UserRegistrationTest {

    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;

    private UserDao dao;
    private UserService service;

    public void setUp() {
        dao = new UserDao();
        dao.clear(); // Reset in-memory database for isolation
        service = new UserServiceImpl(dao);
    }

    public static void main(String[] args) {
        UserRegistrationTest testSuite = new UserRegistrationTest();

        System.out.println("=================================================================");
        System.out.println("          Running Student 1 User Registration Test Suite         ");
        System.out.println("=================================================================\n");

        testSuite.runTest("testRegisterUser_Success", testSuite::testRegisterUser_Success);
        testSuite.runTest("testRegisterUser_DefaultValues", testSuite::testRegisterUser_DefaultValues);
        testSuite.runTest("testRegisterUser_NullUser", testSuite::testRegisterUser_NullUser);
        testSuite.runTest("testValidateUsername_Valid", testSuite::testValidateUsername_Valid);
        testSuite.runTest("testValidateUsername_Invalid", testSuite::testValidateUsername_Invalid);
        testSuite.runTest("testRegisterUser_InvalidUsername", testSuite::testRegisterUser_InvalidUsername);
        testSuite.runTest("testValidateEmail_Valid", testSuite::testValidateEmail_Valid);
        testSuite.runTest("testValidateEmail_Invalid", testSuite::testValidateEmail_Invalid);
        testSuite.runTest("testRegisterUser_InvalidEmail", testSuite::testRegisterUser_InvalidEmail);
        testSuite.runTest("testRegisterUser_InvalidMobile", testSuite::testRegisterUser_InvalidMobile);
        testSuite.runTest("testRegisterUser_InvalidPassword", testSuite::testRegisterUser_InvalidPassword);
        testSuite.runTest("testRegisterUser_DuplicateUsername", testSuite::testRegisterUser_DuplicateUsername);
        testSuite.runTest("testRegisterUser_DuplicateEmail", testSuite::testRegisterUser_DuplicateEmail);
        testSuite.runTest("testRegisterUser_DuplicateMobile", testSuite::testRegisterUser_DuplicateMobile);
        testSuite.runTest("testRegisterUser_UniqueIdGeneration", testSuite::testRegisterUser_UniqueIdGeneration);

        System.out.println("\n=================================================================");
        System.out.println("                        Test Results Summary                     ");
        System.out.println("=================================================================");
        System.out.println(" Total Tests : " + totalTests);
        System.out.println(" Passed      : " + passedTests);
        System.out.println(" Failed      : " + failedTests);
        System.out.println(" Result      : " + (failedTests == 0 ? "ALL TESTS PASSED!" : "SOME TESTS FAILED!"));
        System.out.println("=================================================================");

        if (failedTests > 0) {
            System.exit(1);
        }
    }

    private void runTest(String testName, Runnable testMethod) {
        totalTests++;
        setUp();
        try {
            testMethod.run();
            passedTests++;
            System.out.printf("[PASS] %s%n", testName);
        } catch (Throwable t) {
            failedTests++;
            System.err.printf("[FAIL] %s - Reason: %s%n", testName, t.getMessage());
            t.printStackTrace(System.err);
        }
    }

    public void testRegisterUser_Success() {
        User user = new User();
        user.setUsername("suresh123");
        user.setEmail("suresh@gmail.com");
        user.setMobile("9876543210");
        user.setPassword("Secret@123");

        service.registerUser(user);

        assertTrue("User ID must be generated (> 0)", user.getUserId() > 0);
        User found = dao.findByUsername("suresh123");
        assertNotNull("User must be persisted in DAO", found);
        assertEquals("User username must match", "suresh123", found.getUsername());
        assertEquals("User email must match", "suresh@gmail.com", found.getEmail());
        assertEquals("User mobile must match", "9876543210", found.getMobile());
    }

    public void testRegisterUser_DefaultValues() {
        User user = new User();
        user.setUsername("passenger_demo");
        user.setEmail("passenger@railconnect.com");
        user.setMobile("9812345678");
        user.setPassword("securepass");

        service.registerUser(user);

        assertEquals("Default role must be PASSENGER", "PASSENGER", user.getRole());
        assertTrue("accountLocked must default to false", !user.isAccountLocked());
        assertEquals("loginAttempts must default to 0", 0, user.getLoginAttempts());
    }

    public void testRegisterUser_NullUser() {
        try {
            service.registerUser(null);
            fail("Expected InvalidUserException for null user");
        } catch (InvalidUserException e) {
            assertNotNull("Exception message should be provided", e.getMessage());
        }
    }

    public void testValidateUsername_Valid() {
        assertTrue("Username 'ankit' should be valid", service.validateUsername("ankit"));
        assertTrue("Username 'abc' should be valid", service.validateUsername("abc"));
        assertTrue("Username 'user_123' should be valid", service.validateUsername("user_123"));
    }

    public void testValidateUsername_Invalid() {
        assertTrue("null username must be invalid", !service.validateUsername(null));
        assertTrue("empty username must be invalid", !service.validateUsername(""));
        assertTrue("blank username must be invalid", !service.validateUsername("   "));
        assertTrue("username 'ab' (<3 chars) must be invalid", !service.validateUsername("ab"));
        assertTrue("username ' a ' (<3 non-whitespace chars) must be invalid", !service.validateUsername(" a "));
    }

    public void testRegisterUser_InvalidUsername() {
        User user = new User();
        user.setUsername("ab");
        user.setEmail("valid@email.com");
        user.setMobile("9876543210");
        user.setPassword("ValidPass123");

        try {
            service.registerUser(user);
            fail("Expected InvalidUserException for username < 3 characters");
        } catch (InvalidUserException e) {
            assertTrue("Message should mention username", e.getMessage().toLowerCase().contains("username"));
        }
    }

    public void testValidateEmail_Valid() {
        assertTrue("ankit@gmail.com should be valid", service.validateEmail("ankit@gmail.com"));
        assertTrue("user.name@railconnect.co.in should be valid", service.validateEmail("user.name@railconnect.co.in"));
        assertTrue("user_123+tag@gmail.com should be valid", service.validateEmail("user_123+tag@gmail.com"));
    }

    public void testValidateEmail_Invalid() {
        assertTrue("null email must be invalid", !service.validateEmail(null));
        assertTrue("empty email must be invalid", !service.validateEmail(""));
        assertTrue("blank email must be invalid", !service.validateEmail("   "));
        assertTrue("ankitgmail.com must be invalid", !service.validateEmail("ankitgmail.com"));
        assertTrue("@gmail.com must be invalid", !service.validateEmail("@gmail.com"));
        assertTrue("ankit@ must be invalid", !service.validateEmail("ankit@"));
    }

    public void testRegisterUser_InvalidEmail() {
        User user = new User();
        user.setUsername("valid_user");
        user.setEmail("ankitgmail.com");
        user.setMobile("9876543210");
        user.setPassword("ValidPass123");

        try {
            service.registerUser(user);
            fail("Expected InvalidUserException for invalid email format");
        } catch (InvalidUserException e) {
            assertTrue("Message should mention email", e.getMessage().toLowerCase().contains("email"));
        }
    }

    public void testRegisterUser_InvalidMobile() {
        // Test short mobile
        User user1 = new User();
        user1.setUsername("valid_user");
        user1.setEmail("valid@email.com");
        user1.setMobile("12345");
        user1.setPassword("ValidPass123");

        try {
            service.registerUser(user1);
            fail("Expected InvalidUserException for mobile < 10 digits");
        } catch (InvalidUserException e) {
            assertTrue("Message should mention mobile", e.getMessage().toLowerCase().contains("mobile"));
        }

        // Test non-numeric mobile
        User user2 = new User();
        user2.setUsername("valid_user2");
        user2.setEmail("valid2@email.com");
        user2.setMobile("abcdefghij");
        user2.setPassword("ValidPass123");

        try {
            service.registerUser(user2);
            fail("Expected InvalidUserException for non-digit mobile");
        } catch (InvalidUserException e) {
            assertTrue("Message should mention mobile", e.getMessage().toLowerCase().contains("mobile"));
        }
    }

    public void testRegisterUser_InvalidPassword() {
        User user = new User();
        user.setUsername("valid_user");
        user.setEmail("valid@email.com");
        user.setMobile("9876543210");
        user.setPassword("123"); // < 6 chars

        try {
            service.registerUser(user);
            fail("Expected InvalidPasswordException for short password");
        } catch (InvalidPasswordException e) {
            assertTrue("Message should mention password", e.getMessage().toLowerCase().contains("password"));
        }
    }

    public void testRegisterUser_DuplicateUsername() {
        User user1 = new User();
        user1.setUsername("duplicate_name");
        user1.setEmail("email1@domain.com");
        user1.setMobile("9876543210");
        user1.setPassword("Pass123456");
        service.registerUser(user1);

        User user2 = new User();
        user2.setUsername("DUPLICATE_NAME"); // test case-insensitivity
        user2.setEmail("email2@domain.com");
        user2.setMobile("9876543211");
        user2.setPassword("Pass123456");

        try {
            service.registerUser(user2);
            fail("Expected DuplicateUsernameException for existing username");
        } catch (DuplicateUsernameException e) {
            assertNotNull("Exception message should be present", e.getMessage());
        }
    }

    public void testRegisterUser_DuplicateEmail() {
        User user1 = new User();
        user1.setUsername("user_one");
        user1.setEmail("same@email.com");
        user1.setMobile("9876543210");
        user1.setPassword("Pass123456");
        service.registerUser(user1);

        User user2 = new User();
        user2.setUsername("user_two");
        user2.setEmail("SAME@EMAIL.COM"); // test case-insensitivity
        user2.setMobile("9876543211");
        user2.setPassword("Pass123456");

        try {
            service.registerUser(user2);
            fail("Expected DuplicateEmailException for existing email");
        } catch (DuplicateEmailException e) {
            assertNotNull("Exception message should be present", e.getMessage());
        }
    }

    public void testRegisterUser_DuplicateMobile() {
        User user1 = new User();
        user1.setUsername("user_alpha");
        user1.setEmail("alpha@email.com");
        user1.setMobile("9876543210");
        user1.setPassword("Pass123456");
        service.registerUser(user1);

        User user2 = new User();
        user2.setUsername("user_beta");
        user2.setEmail("beta@email.com");
        user2.setMobile("9876543210"); // duplicate mobile
        user2.setPassword("Pass123456");

        try {
            service.registerUser(user2);
            fail("Expected DuplicateMobileException for existing mobile");
        } catch (DuplicateMobileException e) {
            assertNotNull("Exception message should be present", e.getMessage());
        }
    }

    public void testRegisterUser_UniqueIdGeneration() {
        User user1 = new User();
        user1.setUsername("first_user");
        user1.setEmail("first@email.com");
        user1.setMobile("9876543201");
        user1.setPassword("Pass123456");
        service.registerUser(user1);

        User user2 = new User();
        user2.setUsername("second_user");
        user2.setEmail("second@email.com");
        user2.setMobile("9876543202");
        user2.setPassword("Pass123456");
        service.registerUser(user2);

        assertTrue("User 1 ID must be positive", user1.getUserId() > 0);
        assertTrue("User 2 ID must be positive", user2.getUserId() > 0);
        assertTrue("User 2 ID must be different from User 1 ID", user1.getUserId() != user2.getUserId());
        assertEquals("User 2 ID should increment User 1 ID by 1", user1.getUserId() + 1, user2.getUserId());
    }

    // Helper assertion methods
    private static void assertTrue(String message, boolean condition) {
        if (!condition) {
            throw new AssertionError("Assertion Failed: " + message);
        }
    }

    private static void assertNotNull(String message, Object object) {
        if (object == null) {
            throw new AssertionError("Assertion Failed: " + message + " (expected not null, but was null)");
        }
    }

    private static void assertEquals(String message, Object expected, Object actual) {
        if (expected == null && actual == null) {
            return;
        }
        if (expected != null && expected.equals(actual)) {
            return;
        }
        throw new AssertionError(String.format("Assertion Failed: %s (expected: %s, actual: %s)", message, expected, actual));
    }

    private static void fail(String message) {
        throw new AssertionError("Test Failed: " + message);
    }
}
