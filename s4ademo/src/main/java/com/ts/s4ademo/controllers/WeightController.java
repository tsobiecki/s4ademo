package com.ts.s4ademo.controllers;

import com.ts.s4ademo.dtos.Weight;
import com.ts.s4ademo.services.WeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weight")
@CrossOrigin(origins = "http://localhost:4200")
public class WeightController {

    @Autowired
    private WeightService weightService;

    @GetMapping("/flightNumber/{flightNumber}/date/{date}")
    public Weight getWeight(@PathVariable("flightNumber") Integer flightNumber, @PathVariable("date") String date) {
        return weightService.calculateWeight(flightNumber, date);
    }
}
