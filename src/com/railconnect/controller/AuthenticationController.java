package com.railconnect.controller;

import java.util.Scanner;

public class AuthenticationController {

    private Scanner scanner = new Scanner(System.in);

    public void start() {

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("     AUTHENTICATION MANAGEMENT");
            System.out.println("================================");

            System.out.println("1. Login");
            System.out.println("2. Logout");
            System.out.println("3. Validate Username");
            System.out.println("4. Validate Email");
            System.out.println("5. Validate Mobile");
            System.out.println("6. Validate Password");
            System.out.println("7. Check Account Locked");
            System.out.println("8. Get Login Attempts");
            System.out.println("9. Back");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    login();
                    break;

                case 2:
                    logout();
                    break;

                case 3:
                    validateUsername();
                    break;

                case 4:
                    validateEmail();
                    break;

                case 5:
                    validateMobile();
                    break;

                case 6:
                    validatePassword();
                    break;

                case 7:
                    isAccountLocked();
                    break;

                case 8:
                    getLoginAttempts();
                    break;

                case 9:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // 1. Login
    private void login() {

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // Call proper method from service
    }

    // 2. Logout
    private void logout() {

        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        // Call proper method from service
    }

    // 3. Validate Username
    private void validateUsername() {

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        // Call proper method from service
    }

    // 4. Validate Email
    private void validateEmail() {

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        // Call proper method from service
    }

    // 5. Validate Mobile
    private void validateMobile() {

        System.out.print("Enter Mobile Number: ");
        String mobile = scanner.nextLine();

        // Call proper method from service
    }

    // 6. Validate Password
    private void validatePassword() {

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // Call proper method from service
    }

    // 7. Check Account Locked
    private void isAccountLocked() {

        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        // Call proper method from service
    }

    // 8. Get Login Attempts
    private void getLoginAttempts() {

        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        // Call proper method from service
    }
}