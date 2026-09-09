package com.railconnect.model;

public class Station {

    private int stationId;

    private String stationCode;

    private String stationName;

    private String city;

    private String state;

    public Station() {
    }

    public Station(int stationId,
                   String stationCode,
                   String stationName,
                   String city,
                   String state) {

        this.stationId = stationId;
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.city = city;
        this.state = state;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Station{" +
                "stationId=" + stationId +
                ", stationCode='" + stationCode + '\'' +
                ", stationName='" + stationName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}