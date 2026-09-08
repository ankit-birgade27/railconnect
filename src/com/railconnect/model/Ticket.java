package com.railconnect.model;

import java.time.LocalDate;

public class Ticket {

    private String ticketId;

    private String pnrNumber;

    private Booking booking;

    private Train train;

    private Passenger passenger;

    private Station source;

    private Station destination;

    private LocalDate journeyDate;

    private String seatNumber;

    private String coachNumber;

    public Ticket() {
    }

    public Ticket(String ticketId,
                  String pnrNumber,
                  Booking booking,
                  Train train,
                  Passenger passenger,
                  Station source,
                  Station destination,
                  LocalDate journeyDate,
                  String seatNumber,
                  String coachNumber) {

        this.ticketId = ticketId;
        this.pnrNumber = pnrNumber;
        this.booking = booking;
        this.train = train;
        this.passenger = passenger;
        this.source = source;
        this.destination = destination;
        this.journeyDate = journeyDate;
        this.seatNumber = seatNumber;
        this.coachNumber = coachNumber;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getPnrNumber() {
        return pnrNumber;
    }

    public void setPnrNumber(String pnrNumber) {
        this.pnrNumber = pnrNumber;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Station getSource() {
        return source;
    }

    public void setSource(Station source) {
        this.source = source;
    }

    public Station getDestination() {
        return destination;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    public LocalDate getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(LocalDate journeyDate) {
        this.journeyDate = journeyDate;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getCoachNumber() {
        return coachNumber;
    }

    public void setCoachNumber(String coachNumber) {
        this.coachNumber = coachNumber;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", pnrNumber='" + pnrNumber + '\'' +
                ", booking=" + booking +
                ", train=" + train +
                ", passenger=" + passenger +
                ", source=" + source +
                ", destination=" + destination +
                ", journeyDate=" + journeyDate +
                ", seatNumber='" + seatNumber + '\'' +
                ", coachNumber='" + coachNumber + '\'' +
                '}';
    }
}