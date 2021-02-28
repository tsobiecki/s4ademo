package com.ts.s4ademo.repositories;

import com.ts.s4ademo.entities.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightRepository {

    List<Integer> getFlightNumbers();

    List<String> getDepartureDates();

    Optional<Long> findFlightIdBy(Integer flightNumber, String date);

    List<Flight> getFlightsFor(Flight.IATACode code, String date);

    Flight.IATACode[] getIATACodes();
}
