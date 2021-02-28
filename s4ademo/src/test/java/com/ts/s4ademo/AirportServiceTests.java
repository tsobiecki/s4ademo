package com.ts.s4ademo;

import com.ts.s4ademo.dtos.AirportStats;
import com.ts.s4ademo.entities.BaggageShipment;
import com.ts.s4ademo.entities.Cargo;
import com.ts.s4ademo.entities.CargoShipment;
import com.ts.s4ademo.entities.Flight;
import com.ts.s4ademo.repositories.CargoRepository;
import com.ts.s4ademo.repositories.FlightRepository;
import com.ts.s4ademo.services.AirportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AirportServiceTests {

    @Autowired
    private AirportService service;

    @MockBean
    private FlightRepository flightRepository;
    @MockBean
    private CargoRepository cargoRepository;

    @Test
    public void shouldGetAirportStats() {
        Flight.IATACode code = Flight.IATACode.ANC;
        String date = "2020-10-10";

        AirportStats expected = AirportStats.builder()
                .arrivals(2)
                .departures(2)
                .arrivingBaggages(1221)
                .departuringBaggages(1776)
                .build();

        when(flightRepository.getFlightsFor(code, date)).thenReturn(Arrays.asList(
                Flight.builder()
                        .flightId(1L)
                        .arrivalAirportIATACode(code)
                        .build(),
                Flight.builder()
                        .flightId(2L)
                        .arrivalAirportIATACode(Flight.IATACode.GDN)
                        .departureAirportIATACode(code)
                        .build(),
                Flight.builder()
                        .flightId(3L)
                        .arrivalAirportIATACode(code)
                        .build(),
                Flight.builder()
                        .flightId(4L)
                        .arrivalAirportIATACode(Flight.IATACode.GDN)
                        .departureAirportIATACode(code)
                        .build()
        ));
        when(cargoRepository.findByFlightId(1L)).thenReturn(testCargo(123, 321));
        when(cargoRepository.findByFlightId(2L)).thenReturn(testCargo(234, 432));
        when(cargoRepository.findByFlightId(3L)).thenReturn(testCargo(345, 432));
        when(cargoRepository.findByFlightId(4L)).thenReturn(testCargo(456, 654));

        AirportStats result = service.getStatsFor(code, date);

        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    private Optional<Cargo> testCargo(int baggagePieces, int cargoPieces) {
        return Optional.of(Cargo.builder()
                .baggage(singletonList(testBaggageShipment(baggagePieces)))
                .cargo(singletonList(testCargoShipment(cargoPieces)))
                .build());
    }

    private BaggageShipment testBaggageShipment(int pieces) {
        return BaggageShipment.builder()
                .pieces(pieces)
                .build();
    }

    private CargoShipment testCargoShipment(int pieces) {
        return CargoShipment.builder()
                .pieces(pieces)
                .build();
    }

}
