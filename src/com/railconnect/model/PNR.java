package com.railconnect.model;



import java.time.LocalDateTime;

public class PNR {

    private String pnrNumber;

    private Booking booking;

    private LocalDateTime generatedDate;

    public PNR() {
    }

    public PNR(String pnrNumber,
               Booking booking,
               LocalDateTime generatedDate) {

        this.pnrNumber = pnrNumber;
        this.booking = booking;
        this.generatedDate = generatedDate;
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

    public LocalDateTime getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(LocalDateTime generatedDate) {
        this.generatedDate = generatedDate;
    }

    @Override
    public String toString() {
        return "PNR{" +
                "pnrNumber='" + pnrNumber + '\'' +
                ", booking=" + booking +
                ", generatedDate=" + generatedDate +
                '}';
    }
}