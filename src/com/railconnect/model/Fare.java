package com.railconnect.model;


import java.math.BigDecimal;

public class Fare {

    private String fareId;

    private Train train;
    private Route route;
    private Coach coach;

    private BigDecimal baseFare;
    private BigDecimal reservationCharge;
    private BigDecimal serviceCharge;
    private BigDecimal totalFare;

    public Fare() {
    }

    public Fare(String fareId,
                Train train,
                Route route,
                Coach coach,
                BigDecimal baseFare,
                BigDecimal reservationCharge,
                BigDecimal serviceCharge,
                BigDecimal totalFare) {

        this.fareId = fareId;
        this.train = train;
        this.route = route;
        this.coach = coach;
        this.baseFare = baseFare;
        this.reservationCharge = reservationCharge;
        this.serviceCharge = serviceCharge;
        this.totalFare = totalFare;
    }

    public String getFareId() {
        return fareId;
    }

    public void setFareId(String fareId) {
        this.fareId = fareId;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public BigDecimal getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(BigDecimal baseFare) {
        this.baseFare = baseFare;
    }

    public BigDecimal getReservationCharge() {
        return reservationCharge;
    }

    public void setReservationCharge(BigDecimal reservationCharge) {
        this.reservationCharge = reservationCharge;
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public BigDecimal getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(BigDecimal totalFare) {
        this.totalFare = totalFare;
    }

    @Override
    public String toString() {
        return "Fare{" +
                "fareId='" + fareId + '\'' +
                ", train=" + train +
                ", route=" + route +
                ", coach=" + coach +
                ", baseFare=" + baseFare +
                ", reservationCharge=" + reservationCharge +
                ", serviceCharge=" + serviceCharge +
                ", totalFare=" + totalFare +
                '}';
    }
}