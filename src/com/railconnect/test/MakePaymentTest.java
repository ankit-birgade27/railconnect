package com.railconnect.test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.railconnect.controller.PaymentController;
import com.railconnect.dao.PaymentDao;
import com.railconnect.enums.PaymentStatus;
import com.railconnect.exception.BookingNotFoundException;
import com.railconnect.exception.InvalidBookingIdException;
import com.railconnect.exception.InvalidPaymentAmountException;
import com.railconnect.model.Booking;
import com.railconnect.model.Payment;
import com.railconnect.service.PaymentService;
import com.railconnect.serviceimpl.PaymentServiceImpl;

public class MakePaymentTest {

    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;

    private PaymentDao paymentDao;
    private PaymentService paymentService;
    private PaymentController paymentController;

    public void setUp() {
        paymentDao = new PaymentDao();
        paymentDao.clear();

        paymentService = new PaymentServiceImpl(paymentDao);
        paymentController = new PaymentController(paymentService);

        // Seed sample booking
        Booking booking = new Booking();
        booking.setBookingId("B1001");
        booking.setBookingStatus("PENDING");
        booking.setTotalFare(new BigDecimal("1500.00"));
        paymentDao.saveBooking(booking);
    }

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("          Running Group 1: Make Payment Test Suite               ");
        System.out.println("=================================================================\n");

        MakePaymentTest test = new MakePaymentTest();

        test.testSuccessfulPayment_Creation();
        test.testSuccessfulPayment_FieldsValidation();
        test.testSuccessfulPayment_BookingUpdated();
        test.testSuccessfulPayment_DaoRetrieval();
        test.testValidation_NullBookingIdThrowsInvalidBookingId();
        test.testValidation_BlankBookingIdThrowsInvalidBookingId();
        test.testValidation_NullAmountThrowsInvalidPaymentAmount();
        test.testValidation_ZeroAmountThrowsInvalidPaymentAmount();
        test.testValidation_NegativeAmountThrowsInvalidPaymentAmount();
        test.testValidation_NonExistentBookingThrowsBookingNotFound();
        test.testService_isPaymentSuccessful();
        test.testService_refundPayment();
        test.testController_makePaymentSuccess();
        test.testController_makePaymentGracefulOnError();

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

    private void assertTrue(String testName, boolean condition, String message) {
        totalTests++;
        if (condition) {
            System.out.println("[PASS] " + testName);
            passedTests++;
        } else {
            System.err.println("[FAIL] " + testName + " -> " + message);
            failedTests++;
        }
    }

    public void testSuccessfulPayment_Creation() {
        setUp();
        BigDecimal amount = new BigDecimal("1500.00");
        Payment payment = paymentService.makePayment("B1001", amount);

        assertTrue("testSuccessfulPayment_Creation - Not Null", payment != null, "Payment object must not be null");
        assertTrue("testSuccessfulPayment_Creation - PaymentId", payment != null && payment.getPaymentId() != null, "Payment ID must be generated");
    }

    public void testSuccessfulPayment_FieldsValidation() {
        setUp();
        BigDecimal amount = new BigDecimal("1250.50");
        Payment payment = paymentService.makePayment("B1001", amount);

        assertTrue("testFields - Amount", payment != null && amount.compareTo(payment.getAmount()) == 0, "Amount must match");
        assertTrue("testFields - Status SUCCESS", payment != null && PaymentStatus.SUCCESS.equals(payment.getStatus()), "Status must be SUCCESS");
        assertTrue("testFields - TxnId starts with TXN", payment != null && payment.getTransactionId() != null && payment.getTransactionId().startsWith("TXN"), "Transaction ID must start with TXN");
        assertTrue("testFields - Method set", payment != null && payment.getPaymentMethod() != null, "Payment method must be set");
        assertTrue("testFields - Date set", payment != null && payment.getPaymentDate() != null, "Payment date must be set");
        assertTrue("testFields - Booking linked", payment != null && payment.getBooking() != null && "B1001".equals(payment.getBooking().getBookingId()), "Booking must be linked");
    }

    public void testSuccessfulPayment_BookingUpdated() {
        setUp();
        BigDecimal amount = new BigDecimal("1500.00");
        Payment payment = paymentService.makePayment("B1001", amount);

        Booking booking = paymentDao.findBookingById("B1001");
        assertTrue("testBookingUpdated - Status CONFIRMED", booking != null && "CONFIRMED".equalsIgnoreCase(booking.getBookingStatus()), "Booking status must be updated to CONFIRMED");
        assertTrue("testBookingUpdated - Payment reference", booking != null && booking.getPayment() == payment, "Booking must reference the created payment");
    }

    public void testSuccessfulPayment_DaoRetrieval() {
        setUp();
        BigDecimal amount = new BigDecimal("850.00");
        Payment payment = paymentService.makePayment("B1001", amount);

        Payment byId = paymentService.getPaymentById(payment.getPaymentId());
        Payment byBooking = paymentService.getPaymentByBookingId("B1001");

        assertTrue("testDaoRetrieval - ById", byId != null && byId.getPaymentId().equals(payment.getPaymentId()), "Should retrieve payment by ID");
        assertTrue("testDaoRetrieval - ByBookingId", byBooking != null && byBooking.getPaymentId().equals(payment.getPaymentId()), "Should retrieve payment by booking ID");
    }

    public void testValidation_NullBookingIdThrowsInvalidBookingId() {
        setUp();
        boolean caught = false;
        try {
            paymentService.makePayment(null, new BigDecimal("100.00"));
        } catch (InvalidBookingIdException e) {
            caught = true;
        }
        assertTrue("testValidation_NullBookingId", caught, "Expected InvalidBookingIdException for null booking ID");
    }

    public void testValidation_BlankBookingIdThrowsInvalidBookingId() {
        setUp();
        boolean caught = false;
        try {
            paymentService.makePayment("   ", new BigDecimal("100.00"));
        } catch (InvalidBookingIdException e) {
            caught = true;
        }
        assertTrue("testValidation_BlankBookingId", caught, "Expected InvalidBookingIdException for blank booking ID");
    }

    public void testValidation_NullAmountThrowsInvalidPaymentAmount() {
        setUp();
        boolean caught = false;
        try {
            paymentService.makePayment("B1001", null);
        } catch (InvalidPaymentAmountException e) {
            caught = true;
        }
        assertTrue("testValidation_NullAmount", caught, "Expected InvalidPaymentAmountException for null amount");
    }

    public void testValidation_ZeroAmountThrowsInvalidPaymentAmount() {
        setUp();
        boolean caught = false;
        try {
            paymentService.makePayment("B1001", BigDecimal.ZERO);
        } catch (InvalidPaymentAmountException e) {
            caught = true;
        }
        assertTrue("testValidation_ZeroAmount", caught, "Expected InvalidPaymentAmountException for zero amount");
    }

    public void testValidation_NegativeAmountThrowsInvalidPaymentAmount() {
        setUp();
        boolean caught = false;
        try {
            paymentService.makePayment("B1001", new BigDecimal("-250.00"));
        } catch (InvalidPaymentAmountException e) {
            caught = true;
        }
        assertTrue("testValidation_NegativeAmount", caught, "Expected InvalidPaymentAmountException for negative amount");
    }

    public void testValidation_NonExistentBookingThrowsBookingNotFound() {
        setUp();
        boolean caught = false;
        try {
            paymentService.makePayment("B9999", new BigDecimal("500.00"));
        } catch (BookingNotFoundException e) {
            caught = true;
        }
        assertTrue("testValidation_NonExistentBooking", caught, "Expected BookingNotFoundException for non-existent booking");
    }

    public void testService_isPaymentSuccessful() {
        setUp();
        Payment payment = paymentService.makePayment("B1001", new BigDecimal("200.00"));
        boolean successful = paymentService.isPaymentSuccessful(payment.getPaymentId());
        assertTrue("testService_isPaymentSuccessful", successful, "Payment should be reported successful");
    }

    public void testService_refundPayment() {
        setUp();
        Payment payment = paymentService.makePayment("B1001", new BigDecimal("200.00"));
        paymentService.refundPayment(payment.getPaymentId());
        Payment updated = paymentService.getPaymentById(payment.getPaymentId());
        assertTrue("testService_refundPayment", updated != null && PaymentStatus.REFUNDED.equals(updated.getStatus()), "Status should be REFUNDED");
    }

    public void testController_makePaymentSuccess() {
        setUp();
        Payment p = paymentController.makePayment("B1001", new BigDecimal("1500.00"));
        assertTrue("testController_makePaymentSuccess - Not Null", p != null, "Controller should return payment object on success");
    }

    public void testController_makePaymentGracefulOnError() {
        setUp();
        Payment p = paymentController.makePayment("NON_EXISTENT", new BigDecimal("1500.00"));
        assertTrue("testController_makePaymentGracefulOnError - Null", p == null, "Controller should return null on error without throwing exception");
    }
}
