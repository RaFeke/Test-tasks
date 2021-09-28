package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightFilter {
    List<Flight> excludeDepartureBeforeSomeDate(List<Flight> flightList, LocalDateTime dateTime);

    List<Flight> excludeArrivalBeforeDeparture(List<Flight> flightList);

    List<Flight> groundTimeMoreThanTwoHoursExclude(List<Flight> flightList);
}
