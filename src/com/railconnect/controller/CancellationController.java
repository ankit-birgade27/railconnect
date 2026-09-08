package com.railconnect.controller;

import java.math.BigDecimal;
import java.util.Scanner;

import com.railconnect.service.CancellationService;

public class CancellationController {

    private Scanner scanner = new Scanner(System.in);
    
    private CancellationService cancellationService;

    public CancellationController(CancellationService cancellationService) {
        this.cancellationService = cancellationService;
    }

    public void start() {

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("     CANCELLATION MANAGEMENT");
            System.out.println("================================");

            System.out.println("1. Cancel Booking");
            System.out.println("2. Calculate Refund");
            System.out.println("3. Check Can Cancel Booking");
            System.out.println("4. Get Cancellation Status");
            System.out.println("5. Back");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    cancelBooking();
                    break;

                case 2:
                    calculateRefund();
                    break;

                case 3:
                    canCancelBooking();
                    break;

                case 4:
                    getCancellationStatus();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }


    // 1. Cancel Booking
    private void cancelBooking() {

        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine();

        // Call proper method from service
    }


    // 2. Calculate Refund
    private void calculateRefund() {

        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine();

        // Call proper method from service
    }


    // 3. Check Can Cancel Booking
    private void canCancelBooking() {

        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine();

        // Call proper method from service
    }


    // 4. Get Cancellation Status
    private void getCancellationStatus() {

        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine();

        // Call proper method from service
    }
}