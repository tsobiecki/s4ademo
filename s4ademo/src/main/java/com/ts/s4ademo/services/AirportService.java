package com.ts.s4ademo.services;

import com.ts.s4ademo.dtos.AirportStats;
import com.ts.s4ademo.entities.Flight;

public interface AirportService {

    AirportStats getStatsFor(Flight.IATACode code, String date);

    Flight.IATACode[] getIATACodes();
}
