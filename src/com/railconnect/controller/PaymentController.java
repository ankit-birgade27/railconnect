package com.railconnect.controller;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.railconnect.dao.PaymentDao;
import com.railconnect.exception.BookingNotFoundException;
import com.railconnect.exception.InvalidBookingIdException;
import com.railconnect.exception.InvalidPaymentAmountException;
import com.railconnect.exception.PaymentFailedException;
import com.railconnect.model.Booking;
import com.railconnect.model.Payment;
import com.railconnect.service.PaymentService;
import com.railconnect.serviceimpl.PaymentServiceImpl;

public class PaymentController {

    private Scanner scanner = new Scanner(System.in);
    private PaymentService paymentService;

    public PaymentController() {
        this.paymentService = new PaymentServiceImpl(new PaymentDao());
    }

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

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {

                case 1:
                    makePaymentMenu();
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

    // 1. Make Payment (CLI Menu Handler)
    private void makePaymentMenu() {

        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine();

        System.out.print("Enter Amount: ");
        BigDecimal amount;
        try {
            amount = scanner.nextBigDecimal();
            scanner.nextLine();
        } catch (Exception e) {
            System.err.println("[Error - Invalid Input]: Please enter a valid decimal amount.");
            scanner.nextLine();
            return;
        }

        makePayment(bookingId, amount);
    }

    /**
     * Group 1 Controller Method: Processes payment and returns the Payment object.
     */
    public Payment makePayment(String bookingId, BigDecimal amount) {
        try {
            Payment payment = paymentService.makePayment(bookingId, amount);
            System.out.println();
            System.out.println("================================================================================");
            System.out.println("                               PAYMENT RECEIPT                                  ");
            System.out.println("================================================================================");
            System.out.println("  Payment ID     : " + payment.getPaymentId());
            System.out.println("  Booking ID     : " + (payment.getBooking() != null ? payment.getBooking().getBookingId() : "N/A"));
            System.out.println("  Amount         : Rs. " + payment.getAmount());
            System.out.println("  Payment Method : " + payment.getPaymentMethod());
            System.out.println("  Status         : " + payment.getStatus());
            System.out.println("  Transaction ID : " + payment.getTransactionId());
            String dateFormatted = (payment.getPaymentDate() != null)
                    ? payment.getPaymentDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    : "N/A";
            System.out.println("  Payment Date   : " + dateFormatted);
            System.out.println("================================================================================");
            return payment;
        } catch (InvalidBookingIdException e) {
            System.err.println("[Error - Invalid Booking ID]: " + e.getMessage());
            return null;
        } catch (InvalidPaymentAmountException e) {
            System.err.println("[Error - Invalid Amount]: " + e.getMessage());
            return null;
        } catch (BookingNotFoundException e) {
            System.err.println("[Error - Booking Not Found]: " + e.getMessage());
            return null;
        } catch (PaymentFailedException e) {
            System.err.println("[Error - Payment Failed]: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("[Error - Unexpected]: " + e.getMessage());
            return null;
        }
    }

    // 2. Get Payment By ID
    private void getPaymentById() {

        System.out.print("Enter Payment ID: ");
        String paymentId = scanner.nextLine();

        try {
            Payment payment = paymentService.getPaymentById(paymentId);
            if (payment != null) {
                System.out.println("Payment Details: " + payment);
            } else {
                System.out.println("Payment not found with ID: " + paymentId);
            }
        } catch (Exception e) {
            System.err.println("[Error]: " + e.getMessage());
        }
    }

    // 3. Get Payment By Booking ID
    private void getPaymentByBookingId() {

        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine();

        try {
            Payment payment = paymentService.getPaymentByBookingId(bookingId);
            if (payment != null) {
                System.out.println("Payment Details: " + payment);
            } else {
                System.out.println("No payment found for Booking ID: " + bookingId);
            }
        } catch (Exception e) {
            System.err.println("[Error]: " + e.getMessage());
        }
    }

    // 4. Check Payment Successful
    private void isPaymentSuccessful() {

        System.out.print("Enter Payment ID: ");
        String paymentId = scanner.nextLine();

        try {
            boolean success = paymentService.isPaymentSuccessful(paymentId);
            System.out.println("Payment ID " + paymentId + " is successful: " + success);
        } catch (Exception e) {
            System.err.println("[Error]: " + e.getMessage());
        }
    }

    // 5. Refund Payment
    private void refundPayment() {

        System.out.print("Enter Payment ID: ");
        String paymentId = scanner.nextLine();

        try {
            paymentService.refundPayment(paymentId);
            System.out.println("Refund processed successfully for Payment ID: " + paymentId);
        } catch (Exception e) {
            System.err.println("[Error]: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        PaymentDao paymentDao = new PaymentDao();
        paymentDao.clear();

        PaymentService paymentService = new PaymentServiceImpl(paymentDao);
        PaymentController controller = new PaymentController(paymentService);

        System.out.println("=================================================================");
        System.out.println("     RailConnect - Group 1: Make Payment Demonstration           ");
        System.out.println("=================================================================\n");

        // Seed sample booking
        Booking booking = new Booking();
        booking.setBookingId("B1001");
        booking.setBookingStatus("PENDING");
        booking.setTotalFare(new BigDecimal("1500.00"));
        paymentDao.saveBooking(booking);

        // 1. Successful Payment
        System.out.println("--- Test 1: Successful Payment for Booking B1001 ---");
        controller.makePayment("B1001", new BigDecimal("1500.00"));
        System.out.println();

        // 2. Invalid Booking ID (Empty)
        System.out.println("--- Test 2: Invalid Booking ID (Empty String) ---");
        controller.makePayment("", new BigDecimal("1500.00"));
        System.out.println();

        // 3. Null Amount
        System.out.println("--- Test 3: Null Amount ---");
        controller.makePayment("B1001", null);
        System.out.println();

        // 4. Zero Amount
        System.out.println("--- Test 4: Zero Amount ---");
        controller.makePayment("B1001", BigDecimal.ZERO);
        System.out.println();

        // 5. Negative Amount
        System.out.println("--- Test 5: Negative Amount ---");
        controller.makePayment("B1001", new BigDecimal("-500.00"));
        System.out.println();

        // 6. Non-Existent Booking
        System.out.println("--- Test 6: Non-Existent Booking (B9999) ---");
        controller.makePayment("B9999", new BigDecimal("1500.00"));
        System.out.println();

        System.out.println("=================================================================");
        System.out.println("               Demonstration Completed Successfully              ");
        System.out.println("=================================================================");
    }
}