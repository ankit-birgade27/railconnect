package com.railconnect.controller;

import java.util.List;
import java.util.Scanner;

import com.railconnect.model.Seat;
import com.railconnect.service.SeatService;

public class SeatController {

    private SeatService seatService;

    private Scanner scanner = new Scanner(System.in);

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    public void start() {

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("        SEAT MANAGEMENT");
            System.out.println("================================");

            System.out.println("1. Get Available Seats");
            System.out.println("2. Get Seat By ID");
            System.out.println("3. Get Seat By Number");
            System.out.println("4. Check Seat Availability");
            System.out.println("5. Reserve Seat");
            System.out.println("6. Release Seat");
            System.out.println("7. Back");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    getAvailableSeats();
                    break;

                case 2:
                    getSeatById();
                    break;

                case 3:
                    getSeatByNumber();
                    break;

                case 4:
                    isSeatAvailable();
                    break;

                case 5:
                    reserveSeat();
                    break;

                case 6:
                    releaseSeat();
                    break;

                case 7:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }


    // 1. Get Available Seats
    private void getAvailableSeats() {

        System.out.print("Enter Train ID: ");

        int trainId = scanner.nextInt();
        scanner.nextLine();

        // Call proper method from service
    }


    // 2. Get Seat By ID
    private void getSeatById() {

        System.out.print("Enter Seat ID: ");

        String seatId = scanner.nextLine();

        // Call proper method from service
    }


    // 3. Get Seat By Number
    private void getSeatByNumber() {

        System.out.print("Enter Train ID: ");

        int trainId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Seat Number: ");

        String seatNumber = scanner.nextLine();

        // Call proper method from service
    }


    // 4. Check Seat Availability
    private void isSeatAvailable() {

        System.out.print("Enter Train ID: ");

        int trainId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Seat Number: ");

        String seatNumber = scanner.nextLine();

        // Call proper method from service
    }


    // 5. Reserve Seat
    private void reserveSeat() {

        System.out.print("Enter Seat ID: ");

        String seatId = scanner.nextLine();

        // Call proper method from service
    }


    // 6. Release Seat
    private void releaseSeat() {

        System.out.print("Enter Seat ID: ");

        String seatId = scanner.nextLine();

        // Call proper method from service
    }
}