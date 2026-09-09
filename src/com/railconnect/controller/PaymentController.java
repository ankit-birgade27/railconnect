package com.railconnect.controller;

import java.math.BigDecimal;
import java.util.Scanner;

import com.railconnect.service.PaymentService;

public class PaymentController {

    private Scanner scanner = new Scanner(System.in);
   
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    public void start() {

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("       PAYMENT MANAGEMENT");
            System.out.println("================================");

            System.out.println("1. Make Payment");
            System.out.println("2. Get Payment By ID");
            System.out.println("3. Get Payment By Booking ID");
            System.out.println("4. Check Payment Successful");
            System.out.println("5. Refund Payment");
            System.out.println("6. Back");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    makePayment();
                    break;

                case 2:
                    getPaymentById();
                    break;

                case 3:
                    getPaymentByBookingId();
                    break;

                case 4:
                    isPaymentSuccessful();
                    break;

                case 5:
                    refundPayment();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }


    // 1. Make Payment
    private void makePayment() {

        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine();

        System.out.print("Enter Amount: ");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();

        // Call proper method from service
    }


    // 2. Get Payment By ID
    private void getPaymentById() {

        System.out.print("Enter Payment ID: ");
        String paymentId = scanner.nextLine();

        // Call proper method from service
    }


    // 3. Get Payment By Booking ID
    private void getPaymentByBookingId() {

        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine();

        // Call proper method from service
    }


    // 4. Check Payment Successful
    private void isPaymentSuccessful() {

        System.out.print("Enter Payment ID: ");
        String paymentId = scanner.nextLine();

        // Call proper method from service
    }


    // 5. Refund Payment
    private void refundPayment() {

        System.out.print("Enter Payment ID: ");
        String paymentId = scanner.nextLine();

        // Call proper method from service
    }
}