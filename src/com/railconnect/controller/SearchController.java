package com.railconnect.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.railconnect.dao.SearchDao;
import com.railconnect.dao.TrainDao;
import com.railconnect.exception.InvalidJourneyDateException;
import com.railconnect.exception.InvalidStationException;
import com.railconnect.exception.TrainNotFoundException;
import com.railconnect.model.Coach;
import com.railconnect.model.Route;
import com.railconnect.model.Station;
import com.railconnect.model.Train;
import com.railconnect.service.SearchService;
import com.railconnect.serviceimpl.SearchServiceImpl;

public class SearchController {

    private Scanner scanner = new Scanner(System.in);
    private SearchService searchService;

    public SearchController() {
        this.searchService = new SearchServiceImpl(new SearchDao());
    }

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    public void start() {

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("        TRAIN SEARCH");
            System.out.println("================================");

            System.out.println("1. Search Trains");
            System.out.println("2. Trains By Source");
            System.out.println("3. Trains By Destination");
            System.out.println("4. Get Train Details");
            System.out.println("5. Back");

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
                    searchTrainsMenu();
                    break;

                case 2:
                    getTrainsBySourceMenu();
                    break;

                case 3:
                    getTrainsByDestinationMenu();
                    break;

                case 4:
                    getTrainDetailsMenu();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // 1. Search Trains (CLI Menu Handler)
    private void searchTrainsMenu() {
        System.out.print("Enter Source: ");
        String source = scanner.nextLine();

        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();

        System.out.print("Enter Journey Date (yyyy-mm-dd): ");
        String dateStr = scanner.nextLine();
        LocalDate journeyDate;
        try {
            journeyDate = LocalDate.parse(dateStr.trim());
        } catch (DateTimeParseException e) {
            System.err.println("[Error - Invalid Date Format]: Date must be in format YYYY-MM-DD (e.g. 2026-10-15).");
            return;
        }

        searchTrains(source, destination, journeyDate);
    }

    /**
     * Group 1 Controller Method (void return per specification).
     */
    public void searchTrains(String source, String destination, LocalDate journeyDate) {
        findTrains(source, destination, journeyDate);
    }

    /**
     * Programmatic search method returning the list of matching trains with user-facing logs.
     */
    public List<Train> findTrains(String source, String destination, LocalDate journeyDate) {
        try {
            List<Train> trains = searchService.searchTrains(source, destination, journeyDate);
            System.out.println("\nTrains found from '" + source + "' to '" + destination + "' on " + journeyDate + ":");
            System.out.println("--------------------------------------------------------------------------------");
            for (Train train : trains) {
                String rName = (train.getRoute() != null) ? train.getRoute().getRouteName() : "N/A";
                System.out.println(String.format("  [ID: %-4d | No: %-6s | Name: %-22s | Dep: %-5s | Arr: %-5s | Route: %s]",
                        train.getTrainId(),
                        train.getTrainNumber(),
                        train.getTrainName(),
                        train.getDepartureTime(),
                        train.getArrivalTime(),
                        rName));
            }
            System.out.println("--------------------------------------------------------------------------------");
            return trains;
        } catch (InvalidStationException e) {
            System.err.println("[Error - Invalid Station]: " + e.getMessage());
            return Collections.emptyList();
        } catch (InvalidJourneyDateException e) {
            System.err.println("[Error - Invalid Journey Date]: " + e.getMessage());
            return Collections.emptyList();
        } catch (TrainNotFoundException e) {
            System.err.println("[Error - Train Not Found]: " + e.getMessage());
            return Collections.emptyList();
        } catch (Exception e) {
            System.err.println("[Error - Unexpected]: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    // 2. Get Trains By Source (CLI Menu Handler)
    private void getTrainsBySourceMenu() {
        System.out.print("Enter Source: ");
        String source = scanner.nextLine();
        try {
            List<Train> trains = searchService.getTrainsBySource(source);
            System.out.println("Trains originating/passing through '" + source + "': " + trains.size());
            for (Train t : trains) {
                System.out.println("  " + t);
            }
        } catch (Exception e) {
            System.err.println("[Error]: " + e.getMessage());
        }
    }

    // 3. Get Trains By Destination (CLI Menu Handler)
    private void getTrainsByDestinationMenu() {
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();
        try {
            List<Train> trains = searchService.getTrainsByDestination(destination);
            System.out.println("Trains reaching destination '" + destination + "': " + trains.size());
            for (Train t : trains) {
                System.out.println("  " + t);
            }
        } catch (Exception e) {
            System.err.println("[Error]: " + e.getMessage());
        }
    }

    // 4. Get Train Details (CLI Menu Handler)
    private void getTrainDetailsMenu() {
        System.out.print("Enter Train ID: ");
        int trainId;
        try {
            trainId = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.err.println("Invalid input! Train ID must be a number.");
            scanner.nextLine();
            return;
        }
        try {
            Train train = searchService.getTrainDetails(trainId);
            System.out.println("Train Details: " + train);
        } catch (Exception e) {
            System.err.println("[Error]: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SearchDao searchDao = new SearchDao();
        searchDao.clear();

        SearchService searchService = new SearchServiceImpl(searchDao);
        SearchController controller = new SearchController(searchService);

        System.out.println("=================================================================");
        System.out.println("     RailConnect - Group 1: Search Trains Demonstration          ");
        System.out.println("=================================================================\n");

        // Seed Sample Stations
        Station s1 = new Station(1, "CSMT", "CSMT Mumbai", "Mumbai", "Maharashtra");
        Station s2 = new Station(2, "LNL", "Lonavala", "Lonavala", "Maharashtra");
        Station s3 = new Station(3, "PUNE", "Pune Junction", "Pune", "Maharashtra");

        List<Station> stations1 = new ArrayList<>();
        stations1.add(s1);
        stations1.add(s2);
        stations1.add(s3);

        Route r1 = new Route("R101", "Mumbai - Lonavala - Pune", stations1, 192.0);
        Train t1 = new Train(101, "12127", "Intercity Express", r1, "06:40", "09:57", new ArrayList<>());
        searchDao.saveTrain(t1);

        Station s4 = new Station(4, "NDLS", "New Delhi", "Delhi", "Delhi");
        Station s5 = new Station(5, "CNB", "Kanpur Central", "Kanpur", "Uttar Pradesh");
        Station s6 = new Station(6, "HWH", "Howrah Junction", "Kolkata", "West Bengal");

        List<Station> stations2 = new ArrayList<>();
        stations2.add(s4);
        stations2.add(s5);
        stations2.add(s6);

        Route r2 = new Route("R102", "Delhi - Kanpur - Kolkata", stations2, 1445.0);
        Train t2 = new Train(102, "12302", "Kolkata Rajdhani", r2, "16:50", "09:55", new ArrayList<>());
        searchDao.saveTrain(t2);

        LocalDate futureDate = LocalDate.now().plusDays(5);

        // 1. Successful Search (Direct Terminus to Terminus)
        System.out.println("--- Test 1: Successful Search (CSMT to PUNE) ---");
        controller.searchTrains("CSMT", "PUNE", futureDate);
        System.out.println();

        // 2. Intermediate Station Search (CSMT to Lonavala)
        System.out.println("--- Test 2: Intermediate Station Search (CSMT to LNL) ---");
        controller.searchTrains("CSMT", "LNL", futureDate);
        System.out.println();

        // 3. City Name Station Search
        System.out.println("--- Test 3: City Name Search ('Mumbai' to 'Pune') ---");
        controller.searchTrains("Mumbai", "Pune", futureDate);
        System.out.println();

        // 4. Reverse Direction Travel Check (Pune to Mumbai)
        System.out.println("--- Test 4: Reverse Direction Search (PUNE to CSMT -> Should not match Train 101) ---");
        controller.searchTrains("PUNE", "CSMT", futureDate);
        System.out.println();

        // 5. Past Date Rejection
        System.out.println("--- Test 5: Past Journey Date Rejection ---");
        controller.searchTrains("CSMT", "PUNE", LocalDate.now().minusDays(1));
        System.out.println();

        // 6. Invalid Station Check
        System.out.println("--- Test 6: Non-Existent Station ---");
        controller.searchTrains("CSMT", "XYZ_UNKNOWN", futureDate);
        System.out.println();

        System.out.println("=================================================================");
        System.out.println("               Demonstration Completed Successfully              ");
        System.out.println("=================================================================");
    }
}