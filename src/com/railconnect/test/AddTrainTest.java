package com.railconnect.test;

import java.util.ArrayList;
import java.util.List;

import com.railconnect.dao.TrainDao;
import com.railconnect.exception.DuplicateTrainNumberException;
import com.railconnect.exception.InvalidTrainException;
import com.railconnect.exception.RouteNotFoundException;
import com.railconnect.model.Coach;
import com.railconnect.model.Route;
import com.railconnect.model.Station;
import com.railconnect.model.Train;
import com.railconnect.serviceimpl.TrainServiceImpl;

public class AddTrainTest {

    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;

    private TrainDao trainDao;
    private TrainServiceImpl trainService;

    public void setUp() {
        trainDao = new TrainDao();
        trainDao.clear(); // Reset DataStore.getTrains()
        trainService = new TrainServiceImpl(trainDao);
    }

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("             Running Group 1: Add Train Test Suite              ");
        System.out.println("=================================================================\n");

        AddTrainTest test = new AddTrainTest();

        test.testAddTrain_Success();
        test.testAddTrain_NullTrain();
        test.testAddTrain_BlankTrainNumber();
        test.testAddTrain_DuplicateTrainNumber();
        test.testAddTrain_BlankTrainName();
        test.testAddTrain_ShortTrainName();
        test.testAddTrain_NullRoute();
        test.testAddTrain_BlankRouteId();
        test.testAddTrain_BlankRouteName();
        test.testAddTrain_InvalidDepartureTime();
        test.testAddTrain_InvalidArrivalTime();
        test.testAddTrain_InvalidCoachZeroSeats();
        test.testAddTrain_InvalidCoachBlankNumber();
        test.testAddTrain_AutoGenerateIdAndDefaultCoaches();
        test.testAddTrain_MultipleTrainsUniqueStorage();

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

    public void testAddTrain_Success() {
        setUp();
        Train train = new Train();
        train.setTrainNumber("12127");
        train.setTrainName("Intercity Express");
        train.setRoute(createSampleRoute());
        train.setDepartureTime("06:40");
        train.setArrivalTime("09:57");

        List<Coach> coaches = new ArrayList<>();
        coaches.add(new Coach(1, "C1", "AC Chair Car", 72, new ArrayList<>()));
        train.setCoaches(coaches);

        trainService.addTrain(train);

        Train saved = trainDao.findByTrainNumber("12127");
        assertTrue("testAddTrain_Success - Saved in DAO", saved != null, "Train should be saved in DAO");
        assertTrue("testAddTrain_Success - Train ID Assigned", saved != null && saved.getTrainId() > 0, "Train ID should be positive");
        assertTrue("testAddTrain_Success - Train Name Match", saved != null && "Intercity Express".equals(saved.getTrainName()), "Train name should match");
        assertTrue("testAddTrain_Success - Coaches Count", saved != null && saved.getCoaches().size() == 1, "Coaches count should be 1");
    }

    public void testAddTrain_NullTrain() {
        setUp();
        boolean caught = false;
        try {
            trainService.addTrain(null);
        } catch (InvalidTrainException e) {
            caught = true;
        }
        assertTrue("testAddTrain_NullTrain", caught, "Expected InvalidTrainException on null train");
    }

    public void testAddTrain_BlankTrainNumber() {
        setUp();
        Train train = new Train();
        train.setTrainNumber("   ");
        train.setTrainName("Rajdhani Express");
        train.setRoute(createSampleRoute());
        train.setDepartureTime("16:55");
        train.setArrivalTime("08:35");

        boolean caught = false;
        try {
            trainService.addTrain(train);
        } catch (InvalidTrainException e) {
            caught = true;
        }
        assertTrue("testAddTrain_BlankTrainNumber", caught, "Expected InvalidTrainException for blank train number");
    }

    public void testAddTrain_DuplicateTrainNumber() {
        setUp();
        Train t1 = new Train();
        t1.setTrainNumber("12951");
        t1.setTrainName("Rajdhani Express");
        t1.setRoute(createSampleRoute());
        t1.setDepartureTime("16:55");
        t1.setArrivalTime("08:35");
        trainService.addTrain(t1);

        Train t2 = new Train();
        t2.setTrainNumber("12951"); // duplicate number
        t2.setTrainName("Another Rajdhani");
        t2.setRoute(createSampleRoute());
        t2.setDepartureTime("10:00");
        t2.setArrivalTime("20:00");

        boolean caught = false;
        try {
            trainService.addTrain(t2);
        } catch (DuplicateTrainNumberException e) {
            caught = true;
        }
        assertTrue("testAddTrain_DuplicateTrainNumber", caught, "Expected DuplicateTrainNumberException for duplicate train number");
    }

    public void testAddTrain_BlankTrainName() {
        setUp();
        Train train = new Train();
        train.setTrainNumber("12001");
        train.setTrainName("");
        train.setRoute(createSampleRoute());
        train.setDepartureTime("06:00");
        train.setArrivalTime("11:30");

        boolean caught = false;
        try {
            trainService.addTrain(train);
        } catch (InvalidTrainException e) {
            caught = true;
        }
        assertTrue("testAddTrain_BlankTrainName", caught, "Expected InvalidTrainException for blank train name");
    }

    public void testAddTrain_ShortTrainName() {
        setUp();
        Train train = new Train();
        train.setTrainNumber("12002");
        train.setTrainName("A"); // < 2 chars
        train.setRoute(createSampleRoute());
        train.setDepartureTime("06:00");
        train.setArrivalTime("11:30");

        boolean caught = false;
        try {
            trainService.addTrain(train);
        } catch (InvalidTrainException e) {
            caught = true;
        }
        assertTrue("testAddTrain_ShortTrainName", caught, "Expected InvalidTrainException for train name with < 2 chars");
    }

    public void testAddTrain_NullRoute() {
        setUp();
        Train train = new Train();
        train.setTrainNumber("12003");
        train.setTrainName("Shatabdi Express");
        train.setRoute(null);
        train.setDepartureTime("06:00");
        train.setArrivalTime("11:30");

        boolean caught = false;
        try {
            trainService.addTrain(train);
        } catch (RouteNotFoundException e) {
            caught = true;
        }
        assertTrue("testAddTrain_NullRoute", caught, "Expected RouteNotFoundException when route is null");
    }

    public void testAddTrain_BlankRouteId() {
        setUp();
        Train train = new Train();
        train.setTrainNumber("12004");
        train.setTrainName("Shatabdi Express");
        train.setRoute(new Route("  ", "Valid Route Name", new ArrayList<>(), 100.0));
        train.setDepartureTime("06:00");
        train.setArrivalTime("11:30");

        boolean caught = false;
        try {
            trainService.addTrain(train);
        } catch (RouteNotFoundException e) {
            caught = true;
        }
        assertTrue("testAddTrain_BlankRouteId", caught, "Expected RouteNotFoundException for blank route ID");
    }

    public void testAddTrain_BlankRouteName() {
        setUp();
        Train train = new Train();
        train.setTrainNumber("12005");
        train.setTrainName("Shatabdi Express");
        train.setRoute(new Route("R105", "  ", new ArrayList<>(), 100.0));
        train.setDepartureTime("06:00");
        train.setArrivalTime("11:30");

        boolean caught = false;
        try {
            trainService.addTrain(train);
        } catch (RouteNotFoundException e) {
            caught = true;
        }
        assertTrue("testAddTrain_BlankRouteName", caught, "Expected RouteNotFoundException for blank route name");
    }

    public void testAddTrain_InvalidDepartureTime() {
        setUp();
        Train train = new Train();
        train.setTrainNumber("12006");
        train.setTrainName("Duronto Express");
        train.setRoute(createSampleRoute());
        train.setDepartureTime("25:00"); // invalid hour
        train.setArrivalTime("12:00");

        boolean caught = false;
        try {
            trainService.addTrain(train);
        } catch (InvalidTrainException e) {
            caught = true;
        }
        assertTrue("testAddTrain_InvalidDepartureTime", caught, "Expected InvalidTrainException for invalid departure time");
    }

    public void testAddTrain_InvalidArrivalTime() {
        setUp();
        Train train = new Train();
        train.setTrainNumber("12007");
        train.setTrainName("Duronto Express");
        train.setRoute(createSampleRoute());
        train.setDepartureTime("10:00");
        train.setArrivalTime("12:75"); // invalid minutes

        boolean caught = false;
        try {
            trainService.addTrain(train);
        } catch (InvalidTrainException e) {
            caught = true;
        }
        assertTrue("testAddTrain_InvalidArrivalTime", caught, "Expected InvalidTrainException for invalid arrival time");
    }

    public void testAddTrain_InvalidCoachZeroSeats() {
        setUp();
        Train train = new Train();
        train.setTrainNumber("12008");
        train.setTrainName("Garib Rath");
        train.setRoute(createSampleRoute());
        train.setDepartureTime("10:00");
        train.setArrivalTime("18:00");

        List<Coach> coaches = new ArrayList<>();
        coaches.add(new Coach(1, "G1", "3AC", 0, new ArrayList<>())); // 0 seats
        train.setCoaches(coaches);

        boolean caught = false;
        try {
            trainService.addTrain(train);
        } catch (InvalidTrainException e) {
            caught = true;
        }
        assertTrue("testAddTrain_InvalidCoachZeroSeats", caught, "Expected InvalidTrainException for coach with 0 seats");
    }

    public void testAddTrain_InvalidCoachBlankNumber() {
        setUp();
        Train train = new Train();
        train.setTrainNumber("12009");
        train.setTrainName("Garib Rath");
        train.setRoute(createSampleRoute());
        train.setDepartureTime("10:00");
        train.setArrivalTime("18:00");

        List<Coach> coaches = new ArrayList<>();
        coaches.add(new Coach(1, "  ", "3AC", 64, new ArrayList<>())); // blank coach number
        train.setCoaches(coaches);

        boolean caught = false;
        try {
            trainService.addTrain(train);
        } catch (InvalidTrainException e) {
            caught = true;
        }
        assertTrue("testAddTrain_InvalidCoachBlankNumber", caught, "Expected InvalidTrainException for coach with blank number");
    }

    public void testAddTrain_AutoGenerateIdAndDefaultCoaches() {
        setUp();
        Train train = new Train();
        train.setTrainNumber("12010");
        train.setTrainName("Vande Bharat Express");
        train.setRoute(createSampleRoute());
        train.setDepartureTime("14:30");
        train.setArrivalTime("21:15");
        train.setCoaches(null); // null coaches -> default to empty list

        trainService.addTrain(train);

        Train saved = trainDao.findByTrainNumber("12010");
        assertTrue("testAddTrain_AutoGenerateId - ID Assigned", saved != null && saved.getTrainId() > 0, "ID should be auto-assigned");
        assertTrue("testAddTrain_DefaultCoaches - Empty List", saved != null && saved.getCoaches() != null && saved.getCoaches().isEmpty(), "Coaches should default to empty list");
    }

    public void testAddTrain_MultipleTrainsUniqueStorage() {
        setUp();
        Train t1 = new Train();
        t1.setTrainNumber("20001");
        t1.setTrainName("First Train");
        t1.setRoute(createSampleRoute());
        t1.setDepartureTime("08:00");
        t1.setArrivalTime("12:00");
        trainService.addTrain(t1);

        Train t2 = new Train();
        t2.setTrainNumber("20002");
        t2.setTrainName("Second Train");
        t2.setRoute(createSampleRoute());
        t2.setDepartureTime("09:00");
        t2.setArrivalTime("13:00");
        trainService.addTrain(t2);

        assertTrue("testAddTrain_MultipleTrains - Distinct IDs", t1.getTrainId() != t2.getTrainId(), "Train IDs must be distinct");
        assertTrue("testAddTrain_MultipleTrains - Total Count", trainDao.findAll().size() >= 2, "DAO should contain at least 2 trains");
    }
}
