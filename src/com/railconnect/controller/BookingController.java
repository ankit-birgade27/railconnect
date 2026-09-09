package com.railconnect.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.railconnect.model.Booking;
import com.railconnect.model.Passenger;
import com.railconnect.model.Station;
import com.railconnect.model.Train;
import com.railconnect.service.BookingService;

public class BookingController {

    private BookingService bookingService;

    private Scanner scanner = new Scanner(System.in);

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public void start() {

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("       BOOKING MANAGEMENT");
            System.out.println("================================");

            System.out.println("1. Create Booking");
            System.out.println("2. Get Booking By ID");
            System.out.println("3. Get All Bookings");
            System.out.println("4. Get User Bookings");
            System.out.println("5. Confirm Booking");
            System.out.println("6. Cancel Booking");
            System.out.println("7. Check Booking Exists");
            System.out.println("8. Bookings By Journey Date");
            System.out.println("9. Back");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

            case 1:
                createBooking();
                break;

            case 2:
                getBookingById();
                break;

            case 3:
                getAllBookings();
                break;

            case 4:
                getUserBookings();
                break;

            case 5:
                confirmBooking();
                break;

            case 6:
                cancelBooking();
                break;

            case 7:
                bookingExists();
                break;

            case 8:
                getBookingsByJourneyDate();
                break;

            case 9:
                return;

            default:
                System.out.println("Invalid choice!");
            }
        }
    }

    // 1. Create Booking
    private void createBooking() {

        Booking booking = new Booking();

        System.out.println();
        System.out.println("========== CREATE BOOKING ==========");

        // Booking ID
        System.out.print("Enter Booking ID: ");
        booking.setBookingId(scanner.nextLine());

        // Train
        Train train = new Train();

        System.out.print("Enter Train ID: ");
        train.setTrainId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Enter Train Number: ");
        train.setTrainNumber(scanner.nextLine());

        System.out.print("Enter Train Name: ");
        train.setTrainName(scanner.nextLine());

        booking.setTrain(train);

        // Source Station
        Station source = new Station();

        System.out.print("Enter Source Station ID: ");
        source.setStationId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Enter Source Station Code: ");
        source.setStationCode(scanner.nextLine());

        System.out.print("Enter Source Station Name: ");
        source.setStationName(scanner.nextLine());

        System.out.print("Enter Source City: ");
        source.setCity(scanner.nextLine());

        System.out.print("Enter Source State: ");
        source.setState(scanner.nextLine());

        booking.setSource(source);

        // Destination Station
        Station destination = new Station();

        System.out.print("Enter Destination Station ID: ");
        destination.setStationId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Enter Destination Station Code: ");
        destination.setStationCode(scanner.nextLine());

        System.out.print("Enter Destination Station Name: ");
        destination.setStationName(scanner.nextLine());

        System.out.print("Enter Destination City: ");
        destination.setCity(scanner.nextLine());

        System.out.print("Enter Destination State: ");
        destination.setState(scanner.nextLine());

        booking.setDestination(destination);

        // Journey Date
        System.out.print("Enter Journey Date (yyyy-MM-dd): ");
        String date = scanner.nextLine();

        booking.setJourneyDate(LocalDate.parse(date));

        // Passengers
        System.out.print("Enter Number of Passengers: ");
        int numberOfPassengers = scanner.nextInt();
        scanner.nextLine();

        List<Passenger> passengers = new ArrayList<>();

        for (int i = 1; i <= numberOfPassengers; i++) {

            System.out.println();
            System.out.println("---------- Passenger " + i + " ----------");

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

            passengers.add(passenger);
        }

        booking.setPassengers(passengers);

        // Booking Status
        System.out.print("Enter Booking Status: ");
        booking.setBookingStatus(scanner.nextLine());

        // Total Fare
        System.out.print("Enter Total Fare: ");
        BigDecimal totalFare = scanner.nextBigDecimal();
        scanner.nextLine();

        booking.setTotalFare(totalFare);

        // PNR and Payment
        // These will be handled separately.

        // Call proper method from BookingService

        System.out.println("Booking details captured successfully.");
    }

    // 2. Get Booking By ID
    private void getBookingById() {

        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine();

        // Call proper method from BookingService
    }

    // 3. Get All Bookings
    private void getAllBookings() {

        // Call proper method from BookingService
    }

    // 4. Get User Bookings
    private void getUserBookings() {

        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        // Call proper method from BookingService
    }

    // 5. Confirm Booking
    private void confirmBooking() {

        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine();

        // Call proper method from BookingService
    }

    // 6. Cancel Booking
    private void cancelBooking() {

        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine();

        // Call proper method from BookingService
    }

    // 7. Check Booking Exists
    private void bookingExists() {

        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine();

        // Call proper method from BookingService
    }

    // 8. Get Bookings By Journey Date
    private void getBookingsByJourneyDate() {

        System.out.print("Enter Journey Date (yyyy-MM-dd): ");
        String date = scanner.nextLine();

        LocalDate journeyDate = LocalDate.parse(date);

        // Call proper method from BookingService
    }
}