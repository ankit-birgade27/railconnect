package com.railconnect.maincontroller;



import com.railconnect.controller.UserController;
import com.railconnect.controller.TrainController;
import com.railconnect.controller.SearchController;
import com.railconnect.controller.SeatController;
import com.railconnect.controller.BookingController;
import com.railconnect.controller.CancellationController;
import com.railconnect.controller.FareController;
import com.railconnect.controller.PaymentController;
import com.railconnect.controller.AdminController;

import com.railconnect.dao.UserDao;
import com.railconnect.dao.TrainDao;
import com.railconnect.dao.SearchDao;
import com.railconnect.dao.SeatDao;
import com.railconnect.dao.BookingDao;
import com.railconnect.dao.CancellationDao;
import com.railconnect.dao.FareDao;
import com.railconnect.dao.PaymentDao;
import com.railconnect.dao.AdminDao;

import com.railconnect.service.UserService;
import com.railconnect.service.TrainService;
import com.railconnect.service.SearchService;
import com.railconnect.service.SeatService;
import com.railconnect.service.BookingService;
import com.railconnect.service.CancellationService;
import com.railconnect.service.FareService;
import com.railconnect.service.PaymentService;
import com.railconnect.service.AdminService;
import com.railconnect.serviceimpl.AdminServiceImpl;
import com.railconnect.serviceimpl.BookingServiceImpl;
import com.railconnect.serviceimpl.CancellationServiceImpl;
import com.railconnect.serviceimpl.FareServiceImpl;
import com.railconnect.serviceimpl.PaymentServiceImpl;
import com.railconnect.serviceimpl.SearchServiceImpl;
import com.railconnect.serviceimpl.SeatServiceImpl;
import com.railconnect.serviceimpl.TrainServiceImpl;
import com.railconnect.serviceimpl.UserServiceImpl;

public class Main {

    public static void main(String[] args) {

        // ==========================================
        // DAO OBJECTS
        // ==========================================

        UserDao userDao = new UserDao();

        TrainDao trainDao = new TrainDao();

        SearchDao searchDao = new SearchDao();

        SeatDao seatDao = new SeatDao();

        BookingDao bookingDao = new BookingDao();

        CancellationDao cancellationDao = new CancellationDao();

        FareDao fareDao = new FareDao();

        PaymentDao paymentDao = new PaymentDao();

        AdminDao adminDao = new AdminDao();


        // ==========================================
        // SERVICE OBJECTS
        // ==========================================

        UserService userService =
                new UserServiceImpl(userDao, null);

        TrainService trainService =
                new TrainServiceImpl(trainDao);

        SearchService searchService =
                new SearchServiceImpl(searchDao);

        SeatService seatService =
                new SeatServiceImpl(seatDao);

        BookingService bookingService =
                new BookingServiceImpl(bookingDao);

        CancellationService cancellationService =
                new CancellationServiceImpl(cancellationDao);

        FareService fareService =
                new FareServiceImpl(fareDao);

        PaymentService paymentService =
                new PaymentServiceImpl(paymentDao);

        AdminService adminService =
                new AdminServiceImpl(adminDao);


        // ==========================================
        // CONTROLLER OBJECTS
        // ==========================================

        UserController userController =
                new UserController(userService);

        TrainController trainController =
                new TrainController(trainService);

        SearchController searchController =
                new SearchController(searchService);

        SeatController seatController =
                new SeatController(seatService);

        BookingController bookingController =
                new BookingController(bookingService);

        CancellationController cancellationController =
                new CancellationController(cancellationService);

        FareController fareController =
                new FareController(fareService);

        PaymentController paymentController =
                new PaymentController(paymentService);

        AdminController adminController =
                new AdminController(adminService);


        // ==========================================
        // MAIN / LIBRARY CONTROLLER
        // ==========================================

        RailConnectController railConnectController =
                new RailConnectController(
                        userController,
                        trainController,
                        searchController,
                        seatController,
                        bookingController,
                        cancellationController,
                        fareController,
                        paymentController,
                        adminController
                );


        // ==========================================
        // START APPLICATION
        // ==========================================

        railConnectController.start();
    }
}