package com.ts.s4ademo.controllers;

import com.ts.s4ademo.dtos.Weight;
import com.ts.s4ademo.services.WeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weight")
public class WeightController {

    @Autowired
    private WeightService weightService;

    @GetMapping("/flightNumber/{flightNumber}/date/{date}")
    public Weight getWeight(@PathVariable("flightNumber") Integer flightNumber, @PathVariable("date") String date) {
        System.out.println(flightNumber);
        System.out.println(date);
        return weightService.calculateWeight(flightNumber, date);
    }
}
