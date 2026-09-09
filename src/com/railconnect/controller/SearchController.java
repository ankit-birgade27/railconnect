package com.railconnect.controller;

import java.time.LocalDate;
import java.util.Scanner;

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

        System.out.print("Enter Source: ");
        String source = scanner.nextLine();

        // Call proper method from service
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