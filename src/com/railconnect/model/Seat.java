package com.railconnect.model;



public class Seat {

    private String seatId;

    private String seatNumber;

    private String seatType;

    private String status;

    private Coach coach;

    public Seat() {
    }

    public Seat(String seatId,
                String seatNumber,
                String seatType,
                String status,
                Coach coach) {

        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.status = status;
        this.coach = coach;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId='" + seatId + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", seatType='" + seatType + '\'' +
                ", status='" + status + '\'' +
                ", coach=" + coach +
                '}';
    }
}