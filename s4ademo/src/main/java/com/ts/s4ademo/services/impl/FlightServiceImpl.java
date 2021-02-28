package com.ts.s4ademo.services.impl;

import com.ts.s4ademo.repositories.FlightRepository;
import com.ts.s4ademo.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;


    @Override
    public List<Integer> getFlightNumbers() {
        return flightRepository.getFlightNumbers();
    }

    @Override
    public List<String> getDepartureDates() {
        return flightRepository.getDepartureDates();
    }
}
