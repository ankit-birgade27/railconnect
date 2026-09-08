package com.railconnect.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Booking {

    private String bookingId;

    private Train train;

    private List<Passenger> passengers;

    private Station source;

    private Station destination;

    private LocalDate journeyDate;

    private String bookingStatus;

    private BigDecimal totalFare;

    private PNR pnr;

    private Payment payment;

    public Booking() {
    }

    public Booking(String bookingId,
                   Train train,
                   List<Passenger> passengers,
                   Station source,
                   Station destination,
                   LocalDate journeyDate,
                   String bookingStatus,
                   BigDecimal totalFare,
                   PNR pnr,
                   Payment payment) {

        this.bookingId = bookingId;
        this.train = train;
        this.passengers = passengers;
        this.source = source;
        this.destination = destination;
        this.journeyDate = journeyDate;
        this.bookingStatus = bookingStatus;
        this.totalFare = totalFare;
        this.pnr = pnr;
        this.payment = payment;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
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

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public BigDecimal getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(BigDecimal totalFare) {
        this.totalFare = totalFare;
    }

    public PNR getPnr() {
        return pnr;
    }

    public void setPnr(PNR pnr) {
        this.pnr = pnr;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", train=" + train +
                ", passengers=" + passengers +
                ", source=" + source +
                ", destination=" + destination +
                ", journeyDate=" + journeyDate +
                ", bookingStatus='" + bookingStatus + '\'' +
                ", totalFare=" + totalFare +
                ", pnr=" + pnr +
                ", payment=" + payment +
                '}';
    }
}