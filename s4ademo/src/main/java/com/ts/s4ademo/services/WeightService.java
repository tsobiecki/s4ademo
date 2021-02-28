package com.ts.s4ademo.services;

import com.ts.s4ademo.dtos.Weight;
import com.ts.s4ademo.entities.Flight;

import java.util.Date;
import java.util.List;

public interface WeightService {

    Weight calculateWeight(Integer flightNumber, String date);
}
