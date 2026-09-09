package com.railconnect.controller;


import java.util.Scanner;

import com.railconnect.model.Passenger;
import com.railconnect.model.User;
import com.railconnect.service.UserService;

public class UserController {

    private UserService userService;

    private Scanner scanner = new Scanner(System.in);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void start() {

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("       USER MANAGEMENT");
            System.out.println("================================");

            System.out.println("1. Register User");
            System.out.println("2. View Profile");
            System.out.println("3. Update Profile");
            System.out.println("4. Change Password");
            System.out.println("5. Forgot Password");
            System.out.println("6. Add Passenger");
            System.out.println("7. Update Passenger");
            System.out.println("8. Delete Passenger");
            System.out.println("9. Get Passengers");
            System.out.println("10. Booking History");
            System.out.println("11. Back");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    registerUser();
                    break;

                case 2:
                    viewProfile();
                    break;

                case 3:
                    updateProfile();
                    break;

                case 4:
                    changePassword();
                    break;

                case 5:
                    forgotPassword();
                    break;

                case 6:
                    addPassenger();
                    break;

                case 7:
                    updatePassenger();
                    break;

                case 8:
                    deletePassenger();
                    break;

                case 9:
                    getPassengers();
                    break;

                case 10:
                    getBookingHistory();
                    break;

                case 11:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // 1. Register User
    private void registerUser() {

        User user = new User();

        System.out.print("Enter Username: ");
        user.setUsername(scanner.nextLine());

        System.out.print("Enter Email: ");
        user.setEmail(scanner.nextLine());

        System.out.print("Enter Mobile: ");
        user.setMobile(scanner.nextLine());

        System.out.print("Enter Password: ");
        user.setPassword(scanner.nextLine());
        
        

        // Call proper method from service
    }

    // 2. View Profile
    private void viewProfile() {

        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        try {

            User user = userService.viewProfile(userId);


            System.out.println();
            System.out.println("================================");
            System.out.println("          USER PROFILE");
            System.out.println("================================");

            System.out.println("User ID       : " + user.getUserId());

            System.out.println("Username      : " + user.getUsername());

            System.out.println("Email         : " + user.getEmail());

            System.out.println("Mobile        : " + user.getMobile());

            System.out.println("Role          : " + user.getRole());

            System.out.println("Account Locked: " + user.isAccountLocked());

            System.out.println("Login Attempts: " + user.getLoginAttempts());

            System.out.println("================================");

        }
        catch (IllegalArgumentException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    // 3. Update Profile
    private void updateProfile() {

        User user = new User();

        System.out.print("Enter User ID: ");
        user.setUserId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Enter Username: ");
        user.setUsername(scanner.nextLine());

        System.out.print("Enter Email: ");
        user.setEmail(scanner.nextLine());

        System.out.print("Enter Mobile: ");
        user.setMobile(scanner.nextLine());

        try {

            userService.updateProfile(user);

        }
        catch (IllegalArgumentException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    // 4. Change Password
    private void changePassword() {

        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Old Password: ");
        String oldPassword = scanner.nextLine();

        System.out.print("Enter New Password: ");
        String newPassword = scanner.nextLine();

        // Call proper method from service
    }

    // 5. Forgot Password
    private void forgotPassword() {

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        // Call proper method from service
    }

    // 6. Add Passenger
    private void addPassenger() {

        Passenger passenger = new Passenger();

        System.out.print("Enter Passenger ID: ");
        passenger.setPassengerId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Enter First Name: ");
        passenger.setFirstName(scanner.nextLine());

        System.out.print("Enter Last Name: ");
        passenger.setLastName(scanner.nextLine());

        System.out.print("Enter Age: ");
        passenger.setAge(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Enter Gender: ");
        passenger.setGender(scanner.nextLine());

        System.out.print("Enter Passenger Type: ");
        passenger.setPassengerType(scanner.nextLine());

        System.out.print("Enter ID Proof Type: ");
        passenger.setIdProofType(scanner.nextLine());

        System.out.print("Enter ID Proof Number: ");
        passenger.setIdProofNumber(scanner.nextLine());

        // Call proper method from service
    }

    // 7. Update Passenger
    private void updatePassenger() {

        Passenger passenger = new Passenger();

        System.out.print("Enter Passenger ID: ");
        passenger.setPassengerId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Enter First Name: ");
        passenger.setFirstName(scanner.nextLine());

        System.out.print("Enter Last Name: ");
        passenger.setLastName(scanner.nextLine());

        System.out.print("Enter Age: ");
        passenger.setAge(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Enter Gender: ");
        passenger.setGender(scanner.nextLine());

        System.out.print("Enter Passenger Type: ");
        passenger.setPassengerType(scanner.nextLine());

        System.out.print("Enter ID Proof Type: ");
        passenger.setIdProofType(scanner.nextLine());

        System.out.print("Enter ID Proof Number: ");
        passenger.setIdProofNumber(scanner.nextLine());

        // Call proper method from service
    }

    // 8. Delete Passenger
    private void deletePassenger() {

        System.out.print("Enter Passenger ID: ");
        int passengerId = scanner.nextInt();
        scanner.nextLine();

        // Call proper method from service
    }

    // 9. Get Passengers
    private void getPassengers() {

        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        // Call proper method from service
    }

    // 10. Booking History
    private void getBookingHistory() {

        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        // Call proper method from service
    }
}