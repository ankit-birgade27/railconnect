package com.railconnect.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.railconnect.controller.SearchController;
import com.railconnect.dao.SearchDao;
import com.railconnect.exception.InvalidJourneyDateException;
import com.railconnect.exception.InvalidStationException;
import com.railconnect.exception.TrainNotFoundException;
import com.railconnect.model.Coach;
import com.railconnect.model.Route;
import com.railconnect.model.Station;
import com.railconnect.model.Train;
import com.railconnect.service.SearchService;
import com.railconnect.serviceimpl.SearchServiceImpl;

public class SearchTrainsTest {

    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;

    private SearchDao searchDao;
    private SearchService searchService;
    private SearchController searchController;

    public void setUp() {
        searchDao = new SearchDao();
        searchDao.clear();

        searchService = new SearchServiceImpl(searchDao);
        searchController = new SearchController(searchService);

        // Seed default testing trains
        // Train 1: CSMT -> Lonavala -> Pune
        List<Station> stations1 = new ArrayList<>();
        stations1.add(new Station(1, "CSMT", "CSMT Mumbai", "Mumbai", "Maharashtra"));
        stations1.add(new Station(2, "LNL", "Lonavala", "Lonavala", "Maharashtra"));
        stations1.add(new Station(3, "PUNE", "Pune Junction", "Pune", "Maharashtra"));

        Route r1 = new Route("R101", "Mumbai - Pune Route", stations1, 192.0);
        Train t1 = new Train(101, "12127", "Intercity Express", r1, "06:40", "09:57", new ArrayList<>());
        searchDao.saveTrain(t1);

        // Train 2: Pune -> Solapur (does not go to Mumbai)
        List<Station> stations2 = new ArrayList<>();
        stations2.add(new Station(3, "PUNE", "Pune Junction", "Pune", "Maharashtra"));
        stations2.add(new Station(4, "DD", "Daund Junction", "Daund", "Maharashtra"));
        stations2.add(new Station(5, "SUR", "Solapur", "Solapur", "Maharashtra"));

        Route r2 = new Route("R102", "Pune - Solapur Route", stations2, 263.0);
        Train t2 = new Train(102, "12157", "Hutatma Express", r2, "18:00", "22:00", new ArrayList<>());
        searchDao.saveTrain(t2);

        // Train 3: Delhi -> Kanpur -> Kolkata
        List<Station> stations3 = new ArrayList<>();
        stations3.add(new Station(6, "NDLS", "New Delhi", "Delhi", "Delhi"));
        stations3.add(new Station(7, "CNB", "Kanpur Central", "Kanpur", "Uttar Pradesh"));
        stations3.add(new Station(8, "HWH", "Howrah Junction", "Kolkata", "West Bengal"));

        Route r3 = new Route("R103", "Delhi - Kolkata Route", stations3, 1445.0);
        Train t3 = new Train(103, "12302", "Howrah Rajdhani", r3, "16:50", "09:55", new ArrayList<>());
        searchDao.saveTrain(t3);
    }

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("         Running Group 1: Search Trains Test Suite               ");
        System.out.println("=================================================================\n");

        SearchTrainsTest test = new SearchTrainsTest();

        test.testSuccessfulSearch_DirectRoute();
        test.testSuccessfulSearch_IntermediateStations();
        test.testSuccessfulSearch_CityNames();
        test.testSuccessfulSearch_StationNames();
        test.testSearch_ReverseDirectionTravelNotReturned();
        test.testSearch_IsolationBetweenDifferentRoutes();
        test.testValidation_NullSourceThrowsInvalidStation();
        test.testValidation_BlankSourceThrowsInvalidStation();
        test.testValidation_NullDestinationThrowsInvalidStation();
        test.testValidation_BlankDestinationThrowsInvalidStation();
        test.testValidation_SameSourceAndDestinationThrowsInvalidStation();
        test.testValidation_NonExistentSourceThrowsInvalidStation();
        test.testValidation_NonExistentDestinationThrowsInvalidStation();
        test.testValidation_NullJourneyDateThrowsInvalidJourneyDate();
        test.testValidation_PastJourneyDateThrowsInvalidJourneyDate();
        test.testValidation_TodayJourneyDateAllowed();
        test.testSearch_NoTrainsRunningThrowsTrainNotFound();
        test.testSearch_CaseInsensitiveMatching();
        test.testController_findTrainsSuccess();
        test.testController_findTrainsGracefulOnError();

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

    public void testSuccessfulSearch_DirectRoute() {
        setUp();
        LocalDate journeyDate = LocalDate.now().plusDays(3);
        List<Train> trains = searchService.searchTrains("CSMT", "PUNE", journeyDate);

        assertTrue("testSuccessfulSearch_DirectRoute - Not Null", trains != null, "Result must not be null");
        assertTrue("testSuccessfulSearch_DirectRoute - Size 1", trains != null && trains.size() == 1, "Should find exactly 1 train");
        assertTrue("testSuccessfulSearch_DirectRoute - Match Train 101", trains != null && trains.get(0).getTrainId() == 101, "Should be Train 101");
    }

    public void testSuccessfulSearch_IntermediateStations() {
        setUp();
        LocalDate journeyDate = LocalDate.now().plusDays(2);
        // CSMT to Lonavala (Intermediate)
        List<Train> trains = searchService.searchTrains("CSMT", "LNL", journeyDate);
        assertTrue("testSuccessfulSearch_Intermediate - Found", trains != null && trains.size() == 1, "Should find train for CSMT -> LNL");
        assertTrue("testSuccessfulSearch_Intermediate - ID 101", trains != null && trains.get(0).getTrainId() == 101, "Train ID must match 101");

        // Lonavala to Pune (Intermediate)
        List<Train> trains2 = searchService.searchTrains("LNL", "PUNE", journeyDate);
        assertTrue("testSuccessfulSearch_Intermediate2 - Found", trains2 != null && trains2.size() == 1, "Should find train for LNL -> PUNE");
    }

    public void testSuccessfulSearch_CityNames() {
        setUp();
        LocalDate journeyDate = LocalDate.now().plusDays(1);
        List<Train> trains = searchService.searchTrains("Mumbai", "Pune", journeyDate);
        assertTrue("testSuccessfulSearch_CityNames - Found", trains != null && trains.size() == 1, "Searching by City names should find train");
    }

    public void testSuccessfulSearch_StationNames() {
        setUp();
        LocalDate journeyDate = LocalDate.now().plusDays(4);
        List<Train> trains = searchService.searchTrains("CSMT Mumbai", "Pune Junction", journeyDate);
        assertTrue("testSuccessfulSearch_StationNames - Found", trains != null && trains.size() == 1, "Searching by full station name should find train");
    }

    public void testSearch_ReverseDirectionTravelNotReturned() {
        setUp();
        LocalDate journeyDate = LocalDate.now().plusDays(1);
        // Train 101 travels CSMT -> PUNE. Searching PUNE -> CSMT must NOT return Train 101!
        boolean caught = false;
        try {
            searchService.searchTrains("PUNE", "CSMT", journeyDate);
        } catch (TrainNotFoundException e) {
            caught = true;
        }
        assertTrue("testSearch_ReverseDirectionTravel - TrainNotFound", caught, "Reverse travel should throw TrainNotFoundException when no reverse train exists");
    }

    public void testSearch_IsolationBetweenDifferentRoutes() {
        setUp();
        LocalDate journeyDate = LocalDate.now().plusDays(2);
        List<Train> trains = searchService.searchTrains("NDLS", "HWH", journeyDate);
        assertTrue("testSearch_Isolation - Count 1", trains != null && trains.size() == 1, "Should only return Delhi-Kolkata train");
        assertTrue("testSearch_Isolation - Train 103", trains != null && trains.get(0).getTrainId() == 103, "Should be Train 103");
    }

    public void testValidation_NullSourceThrowsInvalidStation() {
        setUp();
        boolean caught = false;
        try {
            searchService.searchTrains(null, "PUNE", LocalDate.now().plusDays(1));
        } catch (InvalidStationException e) {
            caught = true;
        }
        assertTrue("testValidation_NullSource", caught, "Expected InvalidStationException for null source");
    }

    public void testValidation_BlankSourceThrowsInvalidStation() {
        setUp();
        boolean caught = false;
        try {
            searchService.searchTrains("   ", "PUNE", LocalDate.now().plusDays(1));
        } catch (InvalidStationException e) {
            caught = true;
        }
        assertTrue("testValidation_BlankSource", caught, "Expected InvalidStationException for blank source");
    }

    public void testValidation_NullDestinationThrowsInvalidStation() {
        setUp();
        boolean caught = false;
        try {
            searchService.searchTrains("CSMT", null, LocalDate.now().plusDays(1));
        } catch (InvalidStationException e) {
            caught = true;
        }
        assertTrue("testValidation_NullDestination", caught, "Expected InvalidStationException for null destination");
    }

    public void testValidation_BlankDestinationThrowsInvalidStation() {
        setUp();
        boolean caught = false;
        try {
            searchService.searchTrains("CSMT", "   ", LocalDate.now().plusDays(1));
        } catch (InvalidStationException e) {
            caught = true;
        }
        assertTrue("testValidation_BlankDestination", caught, "Expected InvalidStationException for blank destination");
    }

    public void testValidation_SameSourceAndDestinationThrowsInvalidStation() {
        setUp();
        boolean caught = false;
        try {
            searchService.searchTrains("PUNE", "PUNE", LocalDate.now().plusDays(1));
        } catch (InvalidStationException e) {
            caught = true;
        }
        assertTrue("testValidation_SameSourceAndDestination", caught, "Expected InvalidStationException when source == destination");
    }

    public void testValidation_NonExistentSourceThrowsInvalidStation() {
        setUp();
        boolean caught = false;
        try {
            searchService.searchTrains("UNKNOWN_STN", "PUNE", LocalDate.now().plusDays(1));
        } catch (InvalidStationException e) {
            caught = true;
        }
        assertTrue("testValidation_NonExistentSource", caught, "Expected InvalidStationException for unknown station");
    }

    public void testValidation_NonExistentDestinationThrowsInvalidStation() {
        setUp();
        boolean caught = false;
        try {
            searchService.searchTrains("CSMT", "UNKNOWN_STN", LocalDate.now().plusDays(1));
        } catch (InvalidStationException e) {
            caught = true;
        }
        assertTrue("testValidation_NonExistentDestination", caught, "Expected InvalidStationException for unknown station");
    }

    public void testValidation_NullJourneyDateThrowsInvalidJourneyDate() {
        setUp();
        boolean caught = false;
        try {
            searchService.searchTrains("CSMT", "PUNE", null);
        } catch (InvalidJourneyDateException e) {
            caught = true;
        }
        assertTrue("testValidation_NullJourneyDate", caught, "Expected InvalidJourneyDateException for null date");
    }

    public void testValidation_PastJourneyDateThrowsInvalidJourneyDate() {
        setUp();
        boolean caught = false;
        try {
            searchService.searchTrains("CSMT", "PUNE", LocalDate.now().minusDays(1));
        } catch (InvalidJourneyDateException e) {
            caught = true;
        }
        assertTrue("testValidation_PastJourneyDate", caught, "Expected InvalidJourneyDateException for past date");
    }

    public void testValidation_TodayJourneyDateAllowed() {
        setUp();
        boolean success = false;
        try {
            List<Train> trains = searchService.searchTrains("CSMT", "PUNE", LocalDate.now());
            success = (trains != null && !trains.isEmpty());
        } catch (Exception e) {
            success = false;
        }
        assertTrue("testValidation_TodayJourneyDateAllowed", success, "Today's date should be accepted");
    }

    public void testSearch_NoTrainsRunningThrowsTrainNotFound() {
        setUp();
        // Route between two existing stations on different networks with no connected train: CSMT and Howrah
        boolean caught = false;
        try {
            searchService.searchTrains("CSMT", "HWH", LocalDate.now().plusDays(2));
        } catch (TrainNotFoundException e) {
            caught = true;
        }
        assertTrue("testSearch_NoTrainsRunning", caught, "Expected TrainNotFoundException when no train covers the route");
    }

    public void testSearch_CaseInsensitiveMatching() {
        setUp();
        List<Train> trains = searchService.searchTrains("csmt", "pune", LocalDate.now().plusDays(1));
        assertTrue("testSearch_CaseInsensitive - Found", trains != null && trains.size() == 1, "Lowercase station codes should match");
    }

    public void testController_findTrainsSuccess() {
        setUp();
        List<Train> trains = searchController.findTrains("CSMT", "PUNE", LocalDate.now().plusDays(1));
        assertTrue("testController_findTrainsSuccess - Size 1", trains != null && trains.size() == 1, "Controller should return matching trains");
    }

    public void testController_findTrainsGracefulOnError() {
        setUp();
        List<Train> result = searchController.findTrains(null, "PUNE", LocalDate.now().plusDays(1));
        assertTrue("testController_findTrainsGracefulOnError - Empty List", result != null && result.isEmpty(), "Controller should return empty list on exception");
    }
}
