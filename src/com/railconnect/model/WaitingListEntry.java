package com.railconnect.model;

import java.time.LocalDateTime;

public class WaitingListEntry {

    private String waitingListId;

    private Booking booking;

    private Passenger passenger;

    private Train train;

    private int position;

    private LocalDateTime addedDate;

    private String status;

    public WaitingListEntry() {
    }

    public WaitingListEntry(String waitingListId,
                            Booking booking,
                            Passenger passenger,
                            Train train,
                            int position,
                            LocalDateTime addedDate,
                            String status) {

        this.waitingListId = waitingListId;
        this.booking = booking;
        this.passenger = passenger;
        this.train = train;
        this.position = position;
        this.addedDate = addedDate;
        this.status = status;
    }

    public String getWaitingListId() {
        return waitingListId;
    }

    public void setWaitingListId(String waitingListId) {
        this.waitingListId = waitingListId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WaitingListEntry{" +
                "waitingListId='" + waitingListId + '\'' +
                ", booking=" + booking +
                ", passenger=" + passenger +
                ", train=" + train +
                ", position=" + position +
                ", addedDate=" + addedDate +
                ", status='" + status + '\'' +
                '}';
    }
}