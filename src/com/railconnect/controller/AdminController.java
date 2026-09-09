package com.railconnect.controller;

import java.util.Scanner;

import com.railconnect.service.AdminService;

public class AdminController {

    private Scanner scanner = new Scanner(System.in);
    
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    public void start() {

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("       ADMIN MANAGEMENT");
            System.out.println("================================");

            System.out.println("1. Add User");
            System.out.println("2. Update User");
            System.out.println("3. Delete User");
            System.out.println("4. Get User By ID");
            System.out.println("5. Get All Users");
            System.out.println("6. Add Train");
            System.out.println("7. Update Train");
            System.out.println("8. Delete Train");
            System.out.println("9. Get All Trains");
            System.out.println("10. Back");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addUser();
                    break;

                case 2:
                    updateUser();
                    break;

                case 3:
                    deleteUser();
                    break;

                case 4:
                    getUserById();
                    break;

                case 5:
                    getAllUsers();
                    break;

                case 6:
                    addTrain();
                    break;

                case 7:
                    updateTrain();
                    break;

                case 8:
                    deleteTrain();
                    break;

                case 9:
                    getAllTrains();
                    break;

                case 10:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // 1. Add User
    private void addUser() {

        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        // Take remaining User details here

        // Call proper method from service
    }

    // 2. Update User
    private void updateUser() {

        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        // Take User details here

        // Call proper method from service
    }

    // 3. Delete User
    private void deleteUser() {

        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        // Call proper method from service
    }

    // 4. Get User By ID
    private void getUserById() {

        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        // Call proper method from service
    }

    // 5. Get All Users
    private void getAllUsers() {

        // Call proper method from service
    }

    // 6. Add Train
    private void addTrain() {

        System.out.print("Enter Train ID: ");
        int trainId = scanner.nextInt();
        scanner.nextLine();

        // Take remaining Train details here

        // Call proper method from service
    }

    // 7. Update Train
    private void updateTrain() {

        System.out.print("Enter Train ID: ");
        int trainId = scanner.nextInt();
        scanner.nextLine();

        // Take Train details here

        // Call proper method from service
    }

    // 8. Delete Train
    private void deleteTrain() {

        System.out.print("Enter Train ID: ");
        int trainId = scanner.nextInt();
        scanner.nextLine();

        // Call proper method from service
    }

    // 9. Get All Trains
    private void getAllTrains() {

        // Call proper method from service
    }
}