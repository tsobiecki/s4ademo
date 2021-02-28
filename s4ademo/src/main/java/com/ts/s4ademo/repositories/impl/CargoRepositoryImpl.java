package com.ts.s4ademo.repositories.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ts.s4ademo.entities.Cargo;
import com.ts.s4ademo.repositories.CargoRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CargoRepositoryImpl implements CargoRepository {

    @Override
    public Optional<Cargo> findByFlightId(Long flightId) {
        ObjectMapper mapper = new ObjectMapper();
        List<Cargo> cargos = new ArrayList<>();
        try {
            cargos = mapper.readValue(new File("src/main/resources/cargos.json"), new TypeReference<List<Cargo>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cargos.stream()
                .filter(cargo -> cargo.getFlightId().equals(flightId))
                .findFirst();
    }
}
