package com.railconnect.controller;

import java.math.BigDecimal;
import java.util.Scanner;

import com.railconnect.service.FareService;

public class FareController {
     
    private Scanner scanner = new Scanner(System.in);
    
    private FareService fareService;

    public FareController(FareService fareService) {
        this.fareService = fareService;
    }
    public void start() {

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("         FARE MANAGEMENT");
            System.out.println("================================");

            System.out.println("1. Calculate Fare");
            System.out.println("2. Calculate Passenger Fare");
            System.out.println("3. Get Base Fare");
            System.out.println("4. Get Total Fare");
            System.out.println("5. Back");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    calculateFare();
                    break;

                case 2:
                    calculatePassengerFare();
                    break;

                case 3:
                    getBaseFare();
                    break;

                case 4:
                    getTotalFare();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }


    // 1. Calculate Fare
    private void calculateFare() {

        System.out.print("Enter Train ID: ");
        int trainId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Source: ");
        String source = scanner.nextLine();

        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();

        System.out.print("Enter Number of Passengers: ");
        int numberOfPassengers = scanner.nextInt();
        scanner.nextLine();

        // Call proper method from service
    }


    // 2. Calculate Passenger Fare
    private void calculatePassengerFare() {

        System.out.print("Enter Train ID: ");
        int trainId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Source: ");
        String source = scanner.nextLine();

        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();

        // Call proper method from service
    }


    // 3. Get Base Fare
    private void getBaseFare() {

        System.out.print("Enter Train ID: ");
        int trainId = scanner.nextInt();
        scanner.nextLine();

        // Call proper method from service
    }


    // 4. Get Total Fare
    private void getTotalFare() {

        System.out.print("Enter Fare: ");
        BigDecimal fare = scanner.nextBigDecimal();
        scanner.nextLine();

        System.out.print("Enter Number of Passengers: ");
        int numberOfPassengers = scanner.nextInt();
        scanner.nextLine();

        // Call proper method from service
    }
}