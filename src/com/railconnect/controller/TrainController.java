package com.railconnect.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

import com.railconnect.exception.DuplicateTrainNumberException;

import com.railconnect.exception.InvalidTrainException;
import com.railconnect.exception.TrainNotFoundException;

import com.railconnect.exception.RouteNotFoundException;

import com.railconnect.exception.*;

import com.railconnect.model.Coach;
import com.railconnect.model.Route;
import com.railconnect.model.Station;
import com.railconnect.model.Train;
import com.railconnect.service.TrainService;
import com.railconnect.serviceimpl.TrainServiceImpl;

public class TrainController {

    private TrainService trainService;

    private Scanner scanner = new Scanner(System.in);

    public TrainController() {
        this.trainService = new TrainServiceImpl();
    }

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    public void start() {

        while (true) {

            System.out.println();
            System.out.println("================================");
            System.out.println("        TRAIN MANAGEMENT");
            System.out.println("================================");

            System.out.println("1. Add Train");
            System.out.println("2. Get Train By ID");
            System.out.println("3. Get All Trains");
            System.out.println("4. Update Train");
            System.out.println("5. Delete Train");
            System.out.println("6. Check Train Exists");
            System.out.println("7. Back");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addTrain();
                    break;

                case 2:
                    getTrainById();
                    break;

                case 3:
                    getAllTrains();
                    break;

                case 4:
                    updateTrain();
                    break;

                case 5:
                    deleteTrain();
                    break;

                case 6:
                    trainExists();
                    break;

                case 7:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }


    // 1. Add Train
    private void addTrain() {

        Train train = new Train();

        // Train Details

        System.out.print("Enter Train ID: ");
        train.setTrainId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Enter Train Number: ");
        train.setTrainNumber(scanner.nextLine());

        System.out.print("Enter Train Name: ");
        train.setTrainName(scanner.nextLine());

        System.out.print("Enter Departure Time: ");
        train.setDepartureTime(scanner.nextLine());

        System.out.print("Enter Arrival Time: ");
        train.setArrivalTime(scanner.nextLine());


        // Route Details

        Route route = new Route();

        System.out.print("Enter Route ID: ");
        route.setRouteId(scanner.nextLine());

        System.out.print("Enter Route Name: ");
        route.setRouteName(scanner.nextLine());

        System.out.print("Enter Distance: ");
        route.setDistance(scanner.nextDouble());
        scanner.nextLine();


        // Station Details

        System.out.print("Enter Number of Stations: ");
        int numberOfStations = scanner.nextInt();
        scanner.nextLine();

        List<Station> stations = new ArrayList<>();

        for (int i = 1; i <= numberOfStations; i++) {

            Station station = new Station();

            System.out.println();
            System.out.println("Station " + i);

            System.out.print("Enter Station ID: ");
            station.setStationId(scanner.nextInt());
            scanner.nextLine();

            System.out.print("Enter Station Code: ");
            station.setStationCode(scanner.nextLine());

            System.out.print("Enter Station Name: ");
            station.setStationName(scanner.nextLine());

            System.out.print("Enter City: ");
            station.setCity(scanner.nextLine());

            System.out.print("Enter State: ");
            station.setState(scanner.nextLine());

            stations.add(station);
        }

        route.setStations(stations);

        train.setRoute(route);


        // Coach Details

        System.out.print("Enter Number of Coaches: ");
        int numberOfCoaches = scanner.nextInt();
        scanner.nextLine();

        List<Coach> coaches = new ArrayList<>();

        for (int i = 1; i <= numberOfCoaches; i++) {

            Coach coach = new Coach();

            System.out.println();
            System.out.println("Coach " + i);

            System.out.print("Enter Coach ID: ");
            coach.setCoachId(scanner.nextInt());
            scanner.nextLine();

            System.out.print("Enter Coach Number: ");
            coach.setCoachNumber(scanner.nextLine());

            System.out.print("Enter Coach Type: ");
            coach.setCoachType(scanner.nextLine());

            System.out.print("Enter Total Seats: ");
            coach.setTotalSeats(scanner.nextInt());
            scanner.nextLine();

            coaches.add(coach);
        }

        train.setCoaches(coaches);

        addTrain(train);
    }


    // 2. Get Train By ID
    private void getTrainById() {

        System.out.print("Enter Train ID: ");

        int trainId = scanner.nextInt();
        scanner.nextLine();

        // Call proper method from service
        try {
            Train train = getTrainById(trainId);
            System.out.println("\n========== TRAIN DETAILS ==========");
            System.out.println(train);

        } catch (InvalidTrainException e) {

            System.out.println("Invalid Train ID: " + e.getMessage());

        } catch (TrainNotFoundException e) {

            System.out.println("Train Not Found: " + e.getMessage());
        }
    }
    
    
    public Train getTrainById(int trainId)
            throws InvalidTrainException, TrainNotFoundException {
        return trainService.getTrainById(trainId);
    }
        


    // 3. Get All Trains
    private void getAllTrains() {
        List<Train> trains = trainService.getAllTrains();

        if (trains.isEmpty()) {
            System.out.println("No trains available.");
            return;
        }

        System.out.println("\n========== ALL TRAINS ==========");

        for (Train train : trains) {
            System.out.println(train);
        }
    }


    // 4. Update Train
    private void updateTrain() {

        Train train = new Train();

        // Train Details

        System.out.print("Enter Train ID: ");
        train.setTrainId(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Enter Train Number: ");
        train.setTrainNumber(scanner.nextLine());

        System.out.print("Enter Train Name: ");
        train.setTrainName(scanner.nextLine());

        System.out.print("Enter Departure Time: ");
        train.setDepartureTime(scanner.nextLine());

        System.out.print("Enter Arrival Time: ");
        train.setArrivalTime(scanner.nextLine());


        // Route Details

        Route route = new Route();

        System.out.print("Enter Route ID: ");
        route.setRouteId(scanner.nextLine());

        System.out.print("Enter Route Name: ");
        route.setRouteName(scanner.nextLine());

        System.out.print("Enter Distance: ");
        route.setDistance(scanner.nextDouble());
        scanner.nextLine();


        // Station Details

        System.out.print("Enter Number of Stations: ");
        int numberOfStations = scanner.nextInt();
        scanner.nextLine();

        List<Station> stations = new ArrayList<>();

        for (int i = 1; i <= numberOfStations; i++) {

            Station station = new Station();

            System.out.println();
            System.out.println("Station " + i);

            System.out.print("Enter Station ID: ");
            station.setStationId(scanner.nextInt());
            scanner.nextLine();

            System.out.print("Enter Station Code: ");
            station.setStationCode(scanner.nextLine());

            System.out.print("Enter Station Name: ");
            station.setStationName(scanner.nextLine());

            System.out.print("Enter City: ");
            station.setCity(scanner.nextLine());

            System.out.print("Enter State: ");
            station.setState(scanner.nextLine());

            stations.add(station);
        }

        route.setStations(stations);

        train.setRoute(route);


        // Coach Details

        System.out.print("Enter Number of Coaches: ");
        int numberOfCoaches = scanner.nextInt();
        scanner.nextLine();

        List<Coach> coaches = new ArrayList<>();

        for (int i = 1; i <= numberOfCoaches; i++) {

            Coach coach = new Coach();

            System.out.println();
            System.out.println("Coach " + i);

            System.out.print("Enter Coach ID: ");
            coach.setCoachId(scanner.nextInt());
            scanner.nextLine();

            System.out.print("Enter Coach Number: ");
            coach.setCoachNumber(scanner.nextLine());

            System.out.print("Enter Coach Type: ");
            coach.setCoachType(scanner.nextLine());

            System.out.print("Enter Total Seats: ");
            coach.setTotalSeats(scanner.nextInt());
            scanner.nextLine();

            coaches.add(coach);
        }

        train.setCoaches(coaches);


        // Call proper method from service
        try {
        trainService.updateTrain(train);
        System.out.println("Train updated successfully!");

        } catch (InvalidTrainException |
                 DuplicateTrainNumberException |
                 TrainNotFoundException |
                 RouteNotFoundException e) {

            System.out.println("Update failed: " + e.getMessage());
        }
    }


    // 5. Delete Train
    private void deleteTrain() {

        System.out.print("Enter Train ID: ");

        int trainId = scanner.nextInt();
        scanner.nextLine();

        // Call proper method from service
    }


    // 6. Check Train Exists
    private void trainExists() {

        System.out.print("Enter Train ID: ");

        int trainId = scanner.nextInt();
        scanner.nextLine();

        // Call proper method from service
        boolean exists = trainService.trainExists(trainId);

        System.out.println("Train exists: " + exists);
    }

    /**
     * Group 1 Controller Method: Adds a new train with user-facing validation feedback.
     */
    public void addTrain(Train train) {
        try {
            trainService.addTrain(train);
            System.out.println("Train Added Successfully!");
            System.out.println("  Train ID       : " + train.getTrainId());
            System.out.println("  Train Number   : " + train.getTrainNumber());
            System.out.println("  Train Name     : " + train.getTrainName());
            System.out.println("  Route          : " + (train.getRoute() != null ? train.getRoute().getRouteName() : "N/A"));
            System.out.println("  Departure Time : " + train.getDepartureTime());
            System.out.println("  Arrival Time   : " + train.getArrivalTime());
            System.out.println("  Coaches Count  : " + (train.getCoaches() != null ? train.getCoaches().size() : 0));
        } catch (InvalidTrainException e) {
            System.err.println("[Error - Invalid Train]: " + e.getMessage());
        } catch (DuplicateTrainNumberException e) {
            System.err.println("[Error - Duplicate Train Number]: " + e.getMessage());
        } catch (RouteNotFoundException e) {
            System.err.println("[Error - Route Not Found]: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("[Error - Unexpected]: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TrainController controller = new TrainController();

        System.out.println("=================================================================");
        System.out.println("         RailConnect - Group 1: Add Train Demonstration          ");
        System.out.println("=================================================================\n");

        // 1. Successful Train Addition
        System.out.println("--- Test 1: Successful Train Addition ---");
        Route route1 = new Route("R101", "Mumbai - Pune Express Route", new ArrayList<>(), 192.0);
        List<Coach> coaches1 = new ArrayList<>();
        coaches1.add(new Coach(1, "C1", "AC Chair Car", 72, new ArrayList<>()));
        coaches1.add(new Coach(2, "C2", "Executive Chair Car", 56, new ArrayList<>()));

        Train train1 = new Train();
        train1.setTrainNumber("12127");
        train1.setTrainName("Intercity Superfast Express");
        train1.setRoute(route1);
        train1.setDepartureTime("06:40");
        train1.setArrivalTime("09:57");
        train1.setCoaches(coaches1);
        controller.addTrain(train1);
        System.out.println();

        // 2. Null Train Validation
        System.out.println("--- Test 2: Null Train Object Check ---");
        controller.addTrain(null);
        System.out.println();

        // 3. Missing/Blank Train Number
        System.out.println("--- Test 3: Blank Train Number Check ---");
        Train noNumberTrain = new Train();
        noNumberTrain.setTrainNumber("");
        noNumberTrain.setTrainName("Rajdhani Express");
        noNumberTrain.setRoute(route1);
        noNumberTrain.setDepartureTime("16:55");
        noNumberTrain.setArrivalTime("08:35");
        controller.addTrain(noNumberTrain);
        System.out.println();

        // 4. Duplicate Train Number
        System.out.println("--- Test 4: Duplicate Train Number Check ('12127') ---");
        Train dupTrain = new Train();
        dupTrain.setTrainNumber("12127");
        dupTrain.setTrainName("Duplicate Express");
        dupTrain.setRoute(route1);
        dupTrain.setDepartureTime("10:00");
        dupTrain.setArrivalTime("14:00");
        controller.addTrain(dupTrain);
        System.out.println();

        // 5. Blank/Missing Train Name
        System.out.println("--- Test 5: Blank Train Name Check ---");
        Train blankNameTrain = new Train();
        blankNameTrain.setTrainNumber("12951");
        blankNameTrain.setTrainName(" ");
        blankNameTrain.setRoute(route1);
        blankNameTrain.setDepartureTime("17:00");
        blankNameTrain.setArrivalTime("08:32");
        controller.addTrain(blankNameTrain);
        System.out.println();

        // 6. Missing/Null Route
        System.out.println("--- Test 6: Missing Route Check ---");
        Train noRouteTrain = new Train();
        noRouteTrain.setTrainNumber("12952");
        noRouteTrain.setTrainName("Tejas Express");
        noRouteTrain.setRoute(null);
        noRouteTrain.setDepartureTime("05:30");
        noRouteTrain.setArrivalTime("13:10");
        controller.addTrain(noRouteTrain);
        System.out.println();

        // 7. Invalid Timings Format
        System.out.println("--- Test 7: Invalid Departure Time Format ('25:99') ---");
        Train badTimeTrain = new Train();
        badTimeTrain.setTrainNumber("12953");
        badTimeTrain.setTrainName("August Kranti Rajdhani");
        badTimeTrain.setRoute(route1);
        badTimeTrain.setDepartureTime("25:99");
        badTimeTrain.setArrivalTime("10:00");
        controller.addTrain(badTimeTrain);
        System.out.println();

        // 8. Coach Validation (Zero/Negative seats)
        System.out.println("--- Test 8: Coach Validation (Total Seats <= 0) ---");
        List<Coach> badCoaches = new ArrayList<>();
        badCoaches.add(new Coach(10, "B1", "Sleeper", 0, new ArrayList<>()));
        Train badCoachTrain = new Train();
        badCoachTrain.setTrainNumber("12954");
        badCoachTrain.setTrainName("Garib Rath Express");
        badCoachTrain.setRoute(route1);
        badCoachTrain.setDepartureTime("12:00");
        badCoachTrain.setArrivalTime("18:00");
        badCoachTrain.setCoaches(badCoaches);
        controller.addTrain(badCoachTrain);
        System.out.println();

        // 9. Second Valid Train with Default Auto-Generated ID & Empty Coaches
        System.out.println("--- Test 9: Second Valid Train (Auto ID & Default Coaches) ---");
        Train train2 = new Train();
        train2.setTrainNumber("12955");
        train2.setTrainName("Vande Bharat Express");
        train2.setRoute(route1);
        train2.setDepartureTime("14:30");
        train2.setArrivalTime("21:15");
        controller.addTrain(train2);
        System.out.println();

        System.out.println("=================================================================");
        System.out.println("               Demonstration Completed Successfully              ");
        System.out.println("=================================================================");
    }
}