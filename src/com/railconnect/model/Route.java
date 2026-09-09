package com.railconnect.model;

import java.util.List;

public class Route {

    private String routeId;

    private String routeName;

    private List<Station> stations;

    private double distance;

    public Route() {
    }

    public Route(String routeId,
                 String routeName,
                 List<Station> stations,
                 double distance) {

        this.routeId = routeId;
        this.routeName = routeName;
        this.stations = stations;
        this.distance = distance;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeId='" + routeId + '\'' +
                ", routeName='" + routeName + '\'' +
                ", stations=" + stations +
                ", distance=" + distance +
                '}';
    }
}