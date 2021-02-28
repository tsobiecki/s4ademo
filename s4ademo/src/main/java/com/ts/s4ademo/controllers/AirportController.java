package com.ts.s4ademo.controllers;

import com.ts.s4ademo.dtos.AirportStats;
import com.ts.s4ademo.entities.Flight;
import com.ts.s4ademo.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airport")
@CrossOrigin(origins = "http://localhost:4200")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping("/IATACode/{code}/date/{date}")
    public AirportStats getAiportStatsFor(@PathVariable("code") Flight.IATACode code, @PathVariable("date") String date) {
        return airportService.getStatsFor(code, date);
    }

    @GetMapping("/IATACodes")
    public Flight.IATACode[] getIataCodes() {
        return airportService.getIATACodes();
    }
}
