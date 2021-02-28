package com.ts.s4ademo.controllers;

import com.ts.s4ademo.dtos.AirportStats;
import com.ts.s4ademo.entities.Flight;
import com.ts.s4ademo.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/airport")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @GetMapping("/IATACode/{code}/date{date}")
    public AirportStats getAiportStatsFor(@PathVariable("code") Flight.IATACode code, @PathVariable("date") String date) {
        return airportService.getStatsFor(code, date);
    }
}
