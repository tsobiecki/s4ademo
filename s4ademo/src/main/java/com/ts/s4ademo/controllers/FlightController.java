package com.ts.s4ademo.controllers;

import com.ts.s4ademo.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/flights")
    public List<Integer> getFlights() {
        return flightService.getFlightNumbers();
    }

    @GetMapping("departureDates")
    public List<String> getDepartureDates() {
        return flightService.getDepartureDates();
    }
}
