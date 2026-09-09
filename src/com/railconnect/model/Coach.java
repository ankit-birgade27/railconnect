package com.railconnect.model;



import java.util.List;

public class Coach {

    private int coachId;
    private String coachNumber;
    private String coachType;
    private int totalSeats;
    private List<Seat> seats;

    public Coach() {
    }

    public Coach(int coachId,
                 String coachNumber,
                 String coachType,
                 int totalSeats,
                 List<Seat> seats) {

        this.coachId = coachId;
        this.coachNumber = coachNumber;
        this.coachType = coachType;
        this.totalSeats = totalSeats;
        this.seats = seats;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public String getCoachNumber() {
        return coachNumber;
    }

    public void setCoachNumber(String coachNumber) {
        this.coachNumber = coachNumber;
    }

    public String getCoachType() {
        return coachType;
    }

    public void setCoachType(String coachType) {
        this.coachType = coachType;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Coach{" +
                "coachId=" + coachId +
                ", coachNumber='" + coachNumber + '\'' +
                ", coachType='" + coachType + '\'' +
                ", totalSeats=" + totalSeats +
                ", seats=" + seats +
                '}';
    }
}