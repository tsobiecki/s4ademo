package com.ts.s4ademo.services.impl;

import com.ts.s4ademo.dtos.Weight;
import com.ts.s4ademo.entities.Cargo;
import com.ts.s4ademo.entities.Flight;
import com.ts.s4ademo.repositories.CargoRepository;
import com.ts.s4ademo.repositories.FlightRepository;
import com.ts.s4ademo.services.WeightService;
import com.ts.s4ademo.utils.WeightUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WeightServiceImpl implements WeightService {

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Weight calculateWeight(Integer flightNumber, String date) {
        Long flightId = flightRepository.findFlightIdBy(flightNumber, date)
                .orElseThrow(RuntimeException::new);
        Cargo cargo = cargoRepository.findByFlightId(flightId).orElse(Cargo.builder().build());
        double baggageWeight = cargo.getBaggage().stream()
                .mapToDouble(baggage -> baggage.getWeightUnit() == WeightUnit.lb ? baggage.getWeight() * Weight.LB_TO_KG : baggage.getWeight())
                .sum();
        double cargoWeight = cargo.getCargo().stream()
                .mapToDouble(c -> c.getWeightUnit() == WeightUnit.lb ? c.getWeight() * Weight.LB_TO_KG : c.getWeight())
                .sum();

        return Weight.builder()
                .baggageWeight(baggageWeight)
                .cargoWeight(cargoWeight)
                .totalWeight(baggageWeight + cargoWeight)
                .build();
    }
}
