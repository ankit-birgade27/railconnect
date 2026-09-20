package com.railconnect.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.railconnect.dao.SeatDao;
import com.railconnect.dao.TrainDao;
import com.railconnect.exception.InvalidSeatIdException;
import com.railconnect.exception.InvalidTrainIdException;
import com.railconnect.exception.TrainNotFoundException;
import com.railconnect.model.Coach;
import com.railconnect.model.Route;
import com.railconnect.model.Seat;
import com.railconnect.model.Train;
import com.railconnect.service.SeatService;
import com.railconnect.serviceimpl.SeatServiceImpl;

public class SeatController {

    private SeatService seatService;

    private Scanner scanner = new Scanner(System.in);

    public SeatController() {
        this.seatService = new SeatServiceImpl(new SeatDao());
    }

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


    // 1. Get Available Seats (CLI Menu Handler)
    private void getAvailableSeats() {

        System.out.print("Enter Train ID: ");

        int trainId = scanner.nextInt();
        scanner.nextLine();

        getAvailableSeats(trainId);
    }


    /**
     * Group 1 Controller Method: Retrieves all available seats for a train with user-facing feedback.
     */
    public List<Seat> getAvailableSeats(int trainId) {
        try {
            List<Seat> seats = seatService.getAvailableSeats(trainId);
            System.out.println("Available Seats for Train ID " + trainId + ":");
            if (seats == null || seats.isEmpty()) {
                System.out.println("  No available seats found for this train.");
            } else {
                System.out.println("  Total Available Seats: " + seats.size());
                for (Seat seat : seats) {
                    String coachNum = (seat.getCoach() != null) ? seat.getCoach().getCoachNumber() : "N/A";
                    System.out.println("  [Seat ID: " + seat.getSeatId() + 
                                       ", Seat No: " + seat.getSeatNumber() + 
                                       ", Type: " + seat.getSeatType() + 
                                       ", Status: " + seat.getStatus() + 
                                       ", Coach: " + coachNum + "]");
                }
            }
            return seats;
        } catch (InvalidTrainIdException e) {
            System.err.println("[Error - Invalid Train ID]: " + e.getMessage());
            return Collections.emptyList();
        } catch (TrainNotFoundException e) {
            System.err.println("[Error - Train Not Found]: " + e.getMessage());
            return Collections.emptyList();
        } catch (Exception e) {
            System.err.println("[Error - Unexpected]: " + e.getMessage());
            return Collections.emptyList();
        }
    }


    // 2. Get Seat By ID
    private void getSeatById() {

        System.out.print("Enter Seat ID: ");

        String seatId = scanner.nextLine();

        try {
            Seat seat = seatService.getSeatById(seatId);
            System.out.println("Seat Details: " + seat);
        } catch (Exception e) {
            System.err.println("[Error]: " + e.getMessage());
        }
    }


    // 3. Get Seat By Number
    private void getSeatByNumber() {

        System.out.print("Enter Train ID: ");

        int trainId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Seat Number: ");

        String seatNumber = scanner.nextLine();

        try {
            Seat seat = seatService.getSeatByNumber(trainId, seatNumber);
            if (seat != null) {
                System.out.println("Seat Details: " + seat);
            } else {
                System.out.println("Seat not found.");
            }
        } catch (Exception e) {
            System.err.println("[Error]: " + e.getMessage());
        }
    }


    // 4. Check Seat Availability
    private void isSeatAvailable() {

        System.out.print("Enter Train ID: ");

        int trainId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Seat Number: ");

        String seatNumber = scanner.nextLine();

        try {
            boolean available = seatService.isSeatAvailable(trainId, seatNumber);
            System.out.println("Seat " + seatNumber + " is available: " + available);
        } catch (Exception e) {
            System.err.println("[Error]: " + e.getMessage());
        }
    }


    // 5. Reserve Seat
    private void reserveSeat() {

        System.out.print("Enter Seat ID: ");

        String seatId = scanner.nextLine();

        try {
            seatService.reserveSeat(seatId);
            System.out.println("Seat Reserved Successfully.");
        } catch (Exception e) {
            System.err.println("[Error]: " + e.getMessage());
        }
    }


    // 6. Release Seat
    private void releaseSeat() {

        System.out.print("Enter Seat ID: ");

        String seatId = scanner.nextLine();

        try {
            seatService.releaseSeat(seatId);
            System.out.println("Seat Released Successfully.");
        } catch (Exception e) {
            System.err.println("[Error]: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TrainDao trainDao = new TrainDao();
        trainDao.clear();

        SeatDao seatDao = new SeatDao();
        seatDao.clear();

        SeatService seatService = new SeatServiceImpl(seatDao);
        SeatController controller = new SeatController(seatService);

        System.out.println("=================================================================");
        System.out.println("     RailConnect - Group 1: Get Available Seats Demonstration    ");
        System.out.println("=================================================================\n");

        // Set up sample train with coaches and seats
        Coach c1 = new Coach(1, "C1", "AC Chair Car", 3, new ArrayList<>());
        Seat s1 = new Seat("ST101", "1A", "WINDOW", "AVAILABLE", c1);
        Seat s2 = new Seat("ST102", "1B", "AISLE", "RESERVED", c1);
        Seat s3 = new Seat("ST103", "1C", "MIDDLE", "AVAILABLE", c1);
        c1.getSeats().add(s1);
        c1.getSeats().add(s2);
        c1.getSeats().add(s3);

        List<Coach> coaches = new ArrayList<>();
        coaches.add(c1);

        Train train = new Train(101, "12127", "Intercity Express",
                new Route("R101", "Mumbai - Pune", new ArrayList<>(), 192.0),
                "06:40", "09:57", coaches);
        trainDao.saveTrain(train);

        // 1. Successful Retrieval (Available seats only)
        System.out.println("--- Test 1: Successful Retrieval for Train 101 ---");
        List<Seat> available = controller.getAvailableSeats(101);
        System.out.println("Returned count: " + available.size());
        System.out.println();

        // 2. Negative Train ID Validation
        System.out.println("--- Test 2: Invalid Train ID (-5) ---");
        controller.getAvailableSeats(-5);
        System.out.println();

        // 3. Zero Train ID Validation
        System.out.println("--- Test 3: Invalid Train ID (0) ---");
        controller.getAvailableSeats(0);
        System.out.println();

        // 4. Non-Existent Train ID Check
        System.out.println("--- Test 4: Train Not Found (999) ---");
        controller.getAvailableSeats(999);
        System.out.println();

        // 5. Train with All Reserved Seats
        System.out.println("--- Test 5: Train with All Seats Reserved ---");
        Coach c2 = new Coach(2, "C2", "Sleeper", 1, new ArrayList<>());
        c2.getSeats().add(new Seat("ST201", "2A", "LOWER", "RESERVED", c2));
        List<Coach> coaches2 = new ArrayList<>();
        coaches2.add(c2);
        Train train2 = new Train(102, "12951", "Rajdhani Express",
                new Route("R102", "Delhi - Mumbai", new ArrayList<>(), 1384.0),
                "16:55", "08:35", coaches2);
        trainDao.saveTrain(train2);
        controller.getAvailableSeats(102);
        System.out.println();

        System.out.println("=================================================================");
        System.out.println("               Demonstration Completed Successfully              ");
        System.out.println("=================================================================");
    }
}