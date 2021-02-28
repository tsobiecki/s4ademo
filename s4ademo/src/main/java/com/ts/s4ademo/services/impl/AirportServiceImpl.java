package com.ts.s4ademo.services.impl;

import com.ts.s4ademo.dtos.AirportStats;
import com.ts.s4ademo.entities.BaggageShipment;
import com.ts.s4ademo.entities.CargoShipment;
import com.ts.s4ademo.entities.Flight;
import com.ts.s4ademo.repositories.CargoRepository;
import com.ts.s4ademo.repositories.FlightRepository;
import com.ts.s4ademo.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Override
    public AirportStats getStatsFor(Flight.IATACode code, String date) {
        List<Flight> flights = flightRepository.getFlightsFor(code, date);
        AtomicInteger departures = new AtomicInteger();
        AtomicInteger arrivals = new AtomicInteger();
        AtomicInteger arrivingBaggages = new AtomicInteger();
        AtomicInteger departuringBaggages = new AtomicInteger();
        flights.forEach(flight -> {
            if (flight.getArrivalAirportIATACode().equals(code)) {
                arrivals.getAndIncrement();
                arrivingBaggages.getAndAdd(getTotalPiecesFor(flight.getFlightId()));
            } else {
                departures.getAndIncrement();
                departuringBaggages.getAndAdd(getTotalPiecesFor(flight.getFlightId()));
            }
        });

        return AirportStats.builder()
                .arrivals(arrivals.get())
                .departures(departures.get())
                .arrivingBaggages(arrivingBaggages.get())
                .departuringBaggages(departuringBaggages.get())
                .build();
    }

    private int getTotalPiecesFor(Long flightId) {
        return cargoRepository.findByFlightId(flightId).stream()
                .mapToInt(cargo -> cargo.getBaggage().stream()
                        .mapToInt(BaggageShipment::getPieces)
                        .sum()
                        + cargo.getCargo().stream()
                        .mapToInt(CargoShipment::getPieces)
                        .sum())
                .sum();
    }
}
