package com.railconnect.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.railconnect.model.Coach;
import com.railconnect.model.Route;
import com.railconnect.model.Station;
import com.railconnect.model.Train;
import com.railconnect.service.TrainService;

public class TrainController {

    private TrainService trainService;

    private Scanner scanner = new Scanner(System.in);

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


        // Call proper method from service
    }


    // 2. Get Train By ID
    private void getTrainById() {

        System.out.print("Enter Train ID: ");

        int trainId = scanner.nextInt();
        scanner.nextLine();

        // Call proper method from service
    }


    // 3. Get All Trains
    private void getAllTrains() {

        // Call proper method from service
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
    }
}