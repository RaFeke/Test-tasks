package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightFilter;
import com.gridnine.testing.service.FlightFilterCheck;
import com.gridnine.testing.utillity.Assistant;
import com.gridnine.testing.utillity.FlightBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Flight> flights = FlightBuilder.createFlights();
        Assistant.printFlights(flights);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        FlightFilter flightFilter = new FlightFilterCheck();
        while (true) {
            String input = br.readLine();
            if (input.equals("1")) {
                Assistant.printFlights(flightFilter.excludeDepartureBeforeSomeDate(flights, LocalDateTime.now()));
            }
            if (input.equals("2")) {
                Assistant.printFlights(flightFilter.excludeArrivalBeforeDeparture(flights));
            }
            if (input.equals("3")) {
                Assistant.printFlights(flightFilter.groundTimeMoreThanTwoHoursExclude(flights));
            }
            if (input.equals("4")) {
                br.close();
                return;
            }
        }
    }
}
