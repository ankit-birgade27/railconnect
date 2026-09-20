package com.railconnect.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.railconnect.exception.InvalidStationException;
import com.railconnect.exception.TrainNotFoundException;
import com.railconnect.model.Train;
import com.railconnect.service.SearchService;

public class SearchController {

    private Scanner scanner = new Scanner(System.in);
    
    private SearchService searchService;

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

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    searchTrains();
                    break;

                case 2:
                    getTrainsBySource();
                    break;

                case 3:
                    getTrainsByDestination();
                    break;

                case 4:
                    getTrainDetails();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }


    // 1. Search Trains
    private void searchTrains() {

        System.out.print("Enter Source: ");
        String source = scanner.nextLine();

        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();

        System.out.print("Enter Journey Date (yyyy-mm-dd): ");
        LocalDate journeyDate = LocalDate.parse(scanner.nextLine());

        // Call proper method from service
    }


    // 2. Get Trains By Source
    private void getTrainsBySource() {

        System.out.print("Enter Source Station: ");

        String source = scanner.nextLine();

        getTrainsBySource(source);
    }
    public void getTrainsBySource(String source)
    {
        try
        {
            List<Train> trains =
                    searchService.getTrainsBySource(source);

            System.out.println();
            System.out.println("Trains from source: " + source);
            System.out.println("--------------------------------");

            for (Train train : trains)
            {
                System.out.println("Train ID   : "
                        + train.getTrainId());

                System.out.println("Train No   : "
                        + train.getTrainNumber());

                System.out.println("Train Name : "
                        + train.getTrainName());

                System.out.println("Departure  : "
                        + train.getDepartureTime());

                System.out.println("Arrival    : "
                        + train.getArrivalTime());

                System.out.println("--------------------------------");
            }
        }
        catch (InvalidStationException e) {

            System.out.println(
                    "Invalid Station: " + e.getMessage());

        } catch (TrainNotFoundException e) {

            System.out.println(
                    "Train Not Found: " + e.getMessage());
        }
    }


    // 3. Get Trains By Destination
    private void getTrainsByDestination() {

        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();

        // Call proper method from service
    }


    // 4. Get Train Details
    private void getTrainDetails() {

        System.out.print("Enter Train ID: ");
        int trainId = scanner.nextInt();
        scanner.nextLine();

        // Call proper method from service
    }
}