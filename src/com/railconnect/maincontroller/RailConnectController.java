package com.railconnect.maincontroller;


import java.util.Scanner;

import com.railconnect.controller.AdminController;
import com.railconnect.controller.BookingController;
import com.railconnect.controller.CancellationController;
import com.railconnect.controller.FareController;
import com.railconnect.controller.PaymentController;
import com.railconnect.controller.SearchController;
import com.railconnect.controller.SeatController;
import com.railconnect.controller.TrainController;
import com.railconnect.controller.UserController;

public class RailConnectController {

    private UserController userController;
    private TrainController trainController;
    private SearchController searchController;
    private SeatController seatController;
    private BookingController bookingController;
    private CancellationController cancellationController;
    private FareController fareController;
    private PaymentController paymentController;
    private AdminController adminController;

    private Scanner scanner = new Scanner(System.in);

    public RailConnectController(
            UserController userController,
            TrainController trainController,
            SearchController searchController,
            SeatController seatController,
            BookingController bookingController,
            CancellationController cancellationController,
            FareController fareController,
            PaymentController paymentController,
            AdminController adminController) {

        this.userController = userController;
        this.trainController = trainController;
        this.searchController = searchController;
        this.seatController = seatController;
        this.bookingController = bookingController;
        this.cancellationController = cancellationController;
        this.fareController = fareController;
        this.paymentController = paymentController;
        this.adminController = adminController;
    }

    public void start() {

        while (true) {

            System.out.println();
            System.out.println("======================================");
            System.out.println("       RAILCONNECT RESERVATION");
            System.out.println("======================================");

            System.out.println("1. User Management");
            System.out.println("2. Train Management");
            System.out.println("3. Search Trains");
            System.out.println("4. Seat Management");
            System.out.println("5. Booking Management");
            System.out.println("6. Cancellation");
            System.out.println("7. Fare");
            System.out.println("8. Payment");
            System.out.println("9. Admin");
            System.out.println("10. Exit");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    userController.start();
                    break;

                case 2:
                    trainController.start();
                    break;

                case 3:
                    searchController.start();
                    break;

                case 4:
                    seatController.start();
                    break;

                case 5:
                    bookingController.start();
                    break;

                case 6:
                    cancellationController.start();
                    break;

                case 7:
                    fareController.start();
                    break;

                case 8:
                    paymentController.start();
                    break;

                case 9:
                    adminController.start();
                    break;

                case 10:
                    System.out.println("Thank you for using RailConnect.");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}