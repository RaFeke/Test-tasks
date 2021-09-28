package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilterCheck implements FlightFilter {
    public List<Flight> excludeDepartureBeforeSomeDate(List<Flight> flights, LocalDateTime dateTime) {
        List<Flight> result = new ArrayList<>(flights);
        for (Flight flight : flights) {
            for (Segment segment : flight.getSegments()) {
                if (segment.getDepartureDate().isBefore(dateTime)) {
                    result.remove(flight);
                }
            }
        }
        return result;
    }

    public List<Flight> excludeArrivalBeforeDeparture(List<Flight> flightList) {
        List<Flight> result = new ArrayList<>(flightList);
        for (Flight flight : flightList) {
            List<Segment> segments = flight.getSegments();
            for (Segment segment : segments) {
                if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                    result.remove(flight);
                }
            }
        }
        return result;
    }

    public List<Flight> groundTimeMoreThanTwoHoursExclude(List<Flight> flightList) {
        List<Flight> result = new ArrayList<>(flightList);
        for (Flight flight : flightList) {
            List<Segment> segments = flight.getSegments();
            int time = 0;
            if (segments.size() > 1) {
                for (int i = 0; i < segments.size() - 1; i++) {
                    time += Math.abs(Duration.between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate()).toHours());
                }
                if (segments.size() > 2) {
                    result.remove(flight);
                }
            }
        }
        return result;
    }
}
