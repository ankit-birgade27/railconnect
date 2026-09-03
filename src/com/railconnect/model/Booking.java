package com.railconnect.model;


public class Booking {

    private int bookingId;
    private int userId;
    private int passengerId;

    private String source;
    private String destination;
    private String journeyDate;
    private String seatNumber;

    private double fare;
    private String bookingStatus;

    // Default Constructor
    public Booking() {
    }

    // Parameterized Constructor
    public Booking(int bookingId, int userId, int passengerId,
                   String source, String destination,
                   String journeyDate, String seatNumber,
                   double fare, String bookingStatus) {

        this.bookingId = bookingId;
        this.userId = userId;
        this.passengerId = passengerId;
        this.source = source;
        this.destination = destination;
        this.journeyDate = journeyDate;
        this.seatNumber = seatNumber;
        this.fare = fare;
        this.bookingStatus = bookingStatus;
    }

    // Getters and Setters

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(String journeyDate) {
        this.journeyDate = journeyDate;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", userId=" + userId +
                ", passengerId=" + passengerId +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", journeyDate='" + journeyDate + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", fare=" + fare +
                ", bookingStatus='" + bookingStatus + '\'' +
                '}';
    }
}