package com.ts.s4ademo;

import com.ts.s4ademo.dtos.Weight;
import com.ts.s4ademo.entities.BaggageShipment;
import com.ts.s4ademo.entities.Cargo;
import com.ts.s4ademo.entities.CargoShipment;
import com.ts.s4ademo.repositories.CargoRepository;
import com.ts.s4ademo.repositories.FlightRepository;
import com.ts.s4ademo.services.WeightService;
import com.ts.s4ademo.utils.WeightUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WeightServiceTests {

    @Autowired
    private WeightService service;

    @MockBean
    private FlightRepository flightRepository;
    @MockBean
    private CargoRepository cargoRepository;

    @Test
    public void shouldReturnWeightForGivenFlightNumberAndDate() {
        Long flightId = 1L;
        Integer flightNumber = 123;
        String date = "2020-10-10";

        double expectedCargoWeightInKgs = (321 + 123) * Weight.LB_TO_KG;

        Weight expected = Weight.builder()
                .cargoWeight(expectedCargoWeightInKgs)
                .baggageWeight(444d)
                .totalWeight(expectedCargoWeightInKgs + 444)
                .build();

        when(flightRepository.findFlightIdBy(flightNumber, date))
                .thenReturn(Optional.of(flightId));

        when(cargoRepository.findByFlightId(flightId))
                .thenReturn(Optional.of(Cargo.builder()
                        .flightId(flightId)
                        .baggage(asList(
                                createTestBaggage(123, WeightUnit.kg),
                                createTestBaggage(321, WeightUnit.kg)
                        ))
                        .cargo(asList(
                                createTestCargoShipment(123, WeightUnit.lb),
                                createTestCargoShipment(321, WeightUnit.lb)
                        ))
                        .build()));

        Weight result = service.calculateWeight(flightNumber, date);

        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }


    private BaggageShipment createTestBaggage(Integer weight, WeightUnit unit) {
        return BaggageShipment.builder()
                .id(1L)
                .weight(weight)
                .weightUnit(unit)
                .build();
    }

    private CargoShipment createTestCargoShipment(Integer weight, WeightUnit unit) {
        return CargoShipment.builder()
                .id(1L)
                .weight(weight)
                .weightUnit(unit)
                .build();
    }
}
