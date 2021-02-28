package com.ts.s4ademo.repositories.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ts.s4ademo.entities.Flight;
import com.ts.s4ademo.repositories.FlightRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FlightRepositoryImpl implements FlightRepository {


    @Override
    public List<Integer> getFlightNumbers() {
        return Optional.of(getFlightsFromFile().stream()
                .map(Flight::getFlightNumber)
                .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }

    @Override
    public List<String> getDepartureDates() {
        return Optional.of(getFlightsFromFile().stream()
                .map(flight -> flight.getDepartureDate().toString())
                .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }

    @Override
    public Optional<Long> findFlightIdBy(Integer flightNumber, String date) {
        return getFlightsFromFile().stream()
                .filter(flight -> flight.getFlightNumber().equals(flightNumber) && flight.getDepartureDate().toString().contains(date))
                .map(Flight::getFlightId)
                .findFirst();
    }

    @Override
    public List<Flight> getFlightsFor(Flight.IATACode code, String date) {
        return Optional.of(getFlightsFromFile().stream()
                .filter(flight -> (flight.getArrivalAirportIATACode().equals(code) || flight.getDepartureAirportIATACode().equals(code)) && flight.getDepartureDate().toString().contains(date))
                .collect(Collectors.toList())).orElse(new ArrayList<>());
    }

    @Override
    public Flight.IATACode[] getIATACodes() {
        return Flight.IATACode.values();
    }

    private List<Flight> getFlightsFromFile() {
        ObjectMapper mapper = new ObjectMapper();
        List<Flight> flights = new ArrayList<>();
        try {
            flights = mapper.readValue(new File("src/main/resources/flights.json"), new TypeReference<List<Flight>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flights;
    }
}
