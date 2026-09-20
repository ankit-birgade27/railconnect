package com.railconnect.test;

import java.util.ArrayList;
import java.util.List;

import com.railconnect.controller.SeatController;
import com.railconnect.dao.SeatDao;
import com.railconnect.dao.TrainDao;
import com.railconnect.enums.SeatStatus;
import com.railconnect.exception.InvalidTrainIdException;
import com.railconnect.exception.TrainNotFoundException;
import com.railconnect.model.Coach;
import com.railconnect.model.Route;
import com.railconnect.model.Seat;
import com.railconnect.model.Station;
import com.railconnect.model.Train;
import com.railconnect.service.SeatService;
import com.railconnect.serviceimpl.SeatServiceImpl;

public class GetAvailableSeatsTest {

    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;

    private TrainDao trainDao;
    private SeatDao seatDao;
    private SeatService seatService;
    private SeatController seatController;

    public void setUp() {
        trainDao = new TrainDao();
        trainDao.clear();

        seatDao = new SeatDao();
        seatDao.clear();

        seatService = new SeatServiceImpl(seatDao);
        seatController = new SeatController(seatService);
    }

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("       Running Group 1: Get Available Seats Test Suite           ");
        System.out.println("=================================================================\n");

        GetAvailableSeatsTest test = new GetAvailableSeatsTest();

        test.testSuccessfulRetrievalOfAvailableSeats();
        test.testTrainWithAllSeatsReserved();
        test.testTrainWithNoCoaches();
        test.testTrainWithCoachesHavingNoSeats();
        test.testSeatsAcrossMultipleCoaches();
        test.testIsolationBetweenTrains();
        test.testNegativeTrainIdThrowsInvalidTrainIdException();
        test.testZeroTrainIdThrowsInvalidTrainIdException();
        test.testNonExistentTrainIdThrowsTrainNotFoundException();
        test.testStatusCaseInsensitive();
        test.testNonAvailableStatusesExcluded();
        test.testGlobalDataStoreSeatsMatchingCoach();
        test.testControllerSuccess();
        test.testControllerInvalidTrainId();
        test.testControllerTrainNotFound();

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

    private Route createSampleRoute() {
        List<Station> stations = new ArrayList<>();
        stations.add(new Station(1, "CSTM", "CSMT Mumbai", "Mumbai", "Maharashtra"));
        stations.add(new Station(2, "PUNE", "Pune Junction", "Pune", "Maharashtra"));
        return new Route("R101", "Mumbai - Pune Express Route", stations, 192.0);
    }

    public void testSuccessfulRetrievalOfAvailableSeats() {
        setUp();
        Coach coach = new Coach(1, "C1", "AC Chair Car", 3, new ArrayList<>());
        Seat s1 = new Seat("S101", "1A", "WINDOW", "AVAILABLE", coach);
        Seat s2 = new Seat("S102", "1B", "AISLE", "RESERVED", coach);
        Seat s3 = new Seat("S103", "1C", "MIDDLE", "AVAILABLE", coach);
        coach.getSeats().add(s1);
        coach.getSeats().add(s2);
        coach.getSeats().add(s3);

        List<Coach> coaches = new ArrayList<>();
        coaches.add(coach);

        Train train = new Train(101, "12127", "Intercity Express", createSampleRoute(), "06:40", "09:57", coaches);
        trainDao.saveTrain(train);

        List<Seat> available = seatService.getAvailableSeats(101);

        assertTrue("testSuccessfulRetrieval - Not Null", available != null, "Available seats list must not be null");
        assertTrue("testSuccessfulRetrieval - Count is 2", available != null && available.size() == 2, "Should return 2 available seats");
        assertTrue("testSuccessfulRetrieval - Contains S101", available != null && available.stream().anyMatch(s -> "S101".equals(s.getSeatId())), "Must contain S101");
        assertTrue("testSuccessfulRetrieval - Contains S103", available != null && available.stream().anyMatch(s -> "S103".equals(s.getSeatId())), "Must contain S103");
        assertTrue("testSuccessfulRetrieval - Excludes S102", available != null && available.stream().noneMatch(s -> "S102".equals(s.getSeatId())), "Must not contain reserved S102");
    }

    public void testTrainWithAllSeatsReserved() {
        setUp();
        Coach coach = new Coach(1, "C1", "AC Chair Car", 2, new ArrayList<>());
        coach.getSeats().add(new Seat("S201", "1A", "WINDOW", "RESERVED", coach));
        coach.getSeats().add(new Seat("S202", "1B", "AISLE", "RESERVED", coach));

        List<Coach> coaches = new ArrayList<>();
        coaches.add(coach);

        Train train = new Train(102, "12951", "Rajdhani Express", createSampleRoute(), "16:55", "08:35", coaches);
        trainDao.saveTrain(train);

        List<Seat> available = seatService.getAvailableSeats(102);
        assertTrue("testTrainWithAllSeatsReserved - Empty", available != null && available.isEmpty(), "Should return empty list when all seats are reserved");
    }

    public void testTrainWithNoCoaches() {
        setUp();
        Train train = new Train(103, "12001", "Shatabdi Express", createSampleRoute(), "06:00", "11:30", new ArrayList<>());
        trainDao.saveTrain(train);

        List<Seat> available = seatService.getAvailableSeats(103);
        assertTrue("testTrainWithNoCoaches - Empty", available != null && available.isEmpty(), "Should return empty list when train has no coaches");
    }

    public void testTrainWithCoachesHavingNoSeats() {
        setUp();
        Coach coach = new Coach(1, "C1", "AC Chair Car", 72, new ArrayList<>());
        List<Coach> coaches = new ArrayList<>();
        coaches.add(coach);

        Train train = new Train(104, "12002", "Duronto Express", createSampleRoute(), "07:00", "13:00", coaches);
        trainDao.saveTrain(train);

        List<Seat> available = seatService.getAvailableSeats(104);
        assertTrue("testTrainWithCoachesHavingNoSeats - Empty", available != null && available.isEmpty(), "Should return empty list when coach has no seats added");
    }

    public void testSeatsAcrossMultipleCoaches() {
        setUp();
        Coach c1 = new Coach(1, "C1", "3AC", 2, new ArrayList<>());
        c1.getSeats().add(new Seat("S301", "1A", "LOWER", "AVAILABLE", c1));
        c1.getSeats().add(new Seat("S302", "1B", "UPPER", "RESERVED", c1));

        Coach c2 = new Coach(2, "C2", "2AC", 2, new ArrayList<>());
        c2.getSeats().add(new Seat("S303", "2A", "LOWER", "AVAILABLE", c2));
        c2.getSeats().add(new Seat("S304", "2B", "UPPER", "AVAILABLE", c2));

        List<Coach> coaches = new ArrayList<>();
        coaches.add(c1);
        coaches.add(c2);

        Train train = new Train(105, "12003", "Garib Rath", createSampleRoute(), "10:00", "18:00", coaches);
        trainDao.saveTrain(train);

        List<Seat> available = seatService.getAvailableSeats(105);
        assertTrue("testSeatsAcrossMultipleCoaches - Count is 3", available != null && available.size() == 3, "Should return 3 available seats across 2 coaches");
    }

    public void testIsolationBetweenTrains() {
        setUp();
        Coach c1 = new Coach(1, "C1", "CC", 1, new ArrayList<>());
        c1.getSeats().add(new Seat("TRAIN1_S1", "1A", "WINDOW", "AVAILABLE", c1));
        List<Coach> coaches1 = new ArrayList<>();
        coaches1.add(c1);
        Train t1 = new Train(106, "11111", "Train One", createSampleRoute(), "08:00", "12:00", coaches1);
        trainDao.saveTrain(t1);

        Coach c2 = new Coach(2, "C2", "CC", 1, new ArrayList<>());
        c2.getSeats().add(new Seat("TRAIN2_S1", "1A", "WINDOW", "AVAILABLE", c2));
        List<Coach> coaches2 = new ArrayList<>();
        coaches2.add(c2);
        Train t2 = new Train(107, "22222", "Train Two", createSampleRoute(), "09:00", "13:00", coaches2);
        trainDao.saveTrain(t2);

        List<Seat> seatsT1 = seatService.getAvailableSeats(106);
        List<Seat> seatsT2 = seatService.getAvailableSeats(107);

        assertTrue("testIsolation - Train 1 size 1", seatsT1.size() == 1, "Train 1 must have 1 available seat");
        assertTrue("testIsolation - Train 1 correct seat", "TRAIN1_S1".equals(seatsT1.get(0).getSeatId()), "Train 1 must have TRAIN1_S1");
        assertTrue("testIsolation - Train 2 size 1", seatsT2.size() == 1, "Train 2 must have 1 available seat");
        assertTrue("testIsolation - Train 2 correct seat", "TRAIN2_S1".equals(seatsT2.get(0).getSeatId()), "Train 2 must have TRAIN2_S1");
    }

    public void testNegativeTrainIdThrowsInvalidTrainIdException() {
        setUp();
        boolean caught = false;
        try {
            seatService.getAvailableSeats(-10);
        } catch (InvalidTrainIdException e) {
            caught = true;
        }
        assertTrue("testNegativeTrainId", caught, "Expected InvalidTrainIdException for negative train ID");
    }

    public void testZeroTrainIdThrowsInvalidTrainIdException() {
        setUp();
        boolean caught = false;
        try {
            seatService.getAvailableSeats(0);
        } catch (InvalidTrainIdException e) {
            caught = true;
        }
        assertTrue("testZeroTrainId", caught, "Expected InvalidTrainIdException for train ID zero");
    }

    public void testNonExistentTrainIdThrowsTrainNotFoundException() {
        setUp();
        boolean caught = false;
        try {
            seatService.getAvailableSeats(9999);
        } catch (TrainNotFoundException e) {
            caught = true;
        }
        assertTrue("testNonExistentTrainId", caught, "Expected TrainNotFoundException for non-existent train ID");
    }

    public void testStatusCaseInsensitive() {
        setUp();
        Coach coach = new Coach(1, "C1", "CC", 2, new ArrayList<>());
        coach.getSeats().add(new Seat("S401", "1A", "WINDOW", "available", coach)); // lowercase
        coach.getSeats().add(new Seat("S402", "1B", "AISLE", "Available", coach)); // titlecase

        List<Coach> coaches = new ArrayList<>();
        coaches.add(coach);

        Train train = new Train(108, "12004", "Vande Bharat", createSampleRoute(), "06:00", "14:00", coaches);
        trainDao.saveTrain(train);

        List<Seat> available = seatService.getAvailableSeats(108);
        assertTrue("testStatusCaseInsensitive - Both Found", available != null && available.size() == 2, "Lowercase and mixed case 'available' should be recognized");
    }

    public void testNonAvailableStatusesExcluded() {
        setUp();
        Coach coach = new Coach(1, "C1", "CC", 5, new ArrayList<>());
        coach.getSeats().add(new Seat("S501", "1A", "WINDOW", "AVAILABLE", coach));
        coach.getSeats().add(new Seat("S502", "1B", "AISLE", "BOOKED", coach));
        coach.getSeats().add(new Seat("S503", "1C", "MIDDLE", "BLOCKED", coach));
        coach.getSeats().add(new Seat("S504", "1D", "WINDOW", null, coach));
        coach.getSeats().add(new Seat("S505", "1E", "AISLE", "CANCELLED", coach));

        List<Coach> coaches = new ArrayList<>();
        coaches.add(coach);

        Train train = new Train(109, "12005", "Tejas Express", createSampleRoute(), "15:00", "22:00", coaches);
        trainDao.saveTrain(train);

        List<Seat> available = seatService.getAvailableSeats(109);
        assertTrue("testNonAvailableStatuses - Only AVAILABLE", available != null && available.size() == 1, "Only status AVAILABLE should be returned");
        assertTrue("testNonAvailableStatuses - Correct Seat ID", available != null && "S501".equals(available.get(0).getSeatId()), "S501 must be the only returned seat");
    }

    public void testGlobalDataStoreSeatsMatchingCoach() {
        setUp();
        Coach coach = new Coach(1, "C1", "CC", 2, new ArrayList<>());
        List<Coach> coaches = new ArrayList<>();
        coaches.add(coach);

        Train train = new Train(110, "12006", "Humsafar Express", createSampleRoute(), "11:00", "19:00", coaches);
        trainDao.saveTrain(train);

        // Save seats via seatDao into DataStore.getSeats()
        seatDao.saveSeat(new Seat("G601", "1A", "WINDOW", "AVAILABLE", coach));
        seatDao.saveSeat(new Seat("G602", "1B", "AISLE", "RESERVED", coach));

        List<Seat> available = seatService.getAvailableSeats(110);
        assertTrue("testGlobalDataStoreSeats - Found 1", available != null && available.size() == 1, "Should find 1 available seat from DataStore.getSeats()");
        assertTrue("testGlobalDataStoreSeats - ID Match", available != null && "G601".equals(available.get(0).getSeatId()), "Must be G601");
    }

    public void testControllerSuccess() {
        setUp();
        Coach coach = new Coach(1, "C1", "CC", 1, new ArrayList<>());
        coach.getSeats().add(new Seat("CTRL_S1", "1A", "WINDOW", "AVAILABLE", coach));
        List<Coach> coaches = new ArrayList<>();
        coaches.add(coach);
        Train train = new Train(111, "12007", "Gatimaan Express", createSampleRoute(), "08:10", "09:50", coaches);
        trainDao.saveTrain(train);

        List<Seat> seats = seatController.getAvailableSeats(111);
        assertTrue("testControllerSuccess - Size 1", seats != null && seats.size() == 1, "Controller should return 1 available seat");
        assertTrue("testControllerSuccess - ID Match", seats != null && "CTRL_S1".equals(seats.get(0).getSeatId()), "Seat ID should match");
    }

    public void testControllerInvalidTrainId() {
        setUp();
        List<Seat> seats = seatController.getAvailableSeats(-1);
        assertTrue("testControllerInvalidTrainId - Graceful empty list", seats != null && seats.isEmpty(), "Controller should return empty list on InvalidTrainIdException");
    }

    public void testControllerTrainNotFound() {
        setUp();
        List<Seat> seats = seatController.getAvailableSeats(7777);
        assertTrue("testControllerTrainNotFound - Graceful empty list", seats != null && seats.isEmpty(), "Controller should return empty list on TrainNotFoundException");
    }
}
