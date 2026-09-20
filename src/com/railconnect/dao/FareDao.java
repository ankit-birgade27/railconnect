package com.railconnect.dao;

import java.util.ArrayList;
import java.util.List;

import com.railconnect.model.Fare;
import com.railconnect.model.Station;

public class FareDao {

    private List<Fare> fares = new ArrayList<>();

    public Fare findFare(int trainId, String source, String destination) {

        for (int i = 0; i < fares.size(); i++) {

            Fare fare = fares.get(i);

            if (fare.getTrain().getTrainId() == trainId) {

                List<Station> stations = fare.getRoute().getStations();

                boolean sourceFound = false;
                boolean destinationFound = false;

                for (int j = 0; j < stations.size(); j++) {

                    Station station = stations.get(j);

                    if (station.getStationName().equalsIgnoreCase(source)) {
                        sourceFound = true;
                    }

                    if (station.getStationName().equalsIgnoreCase(destination)) {
                        destinationFound = true;
                    }
                }

                if (sourceFound && destinationFound) {
                    return fare;
                }
            }
        }

        return null;
    }
}