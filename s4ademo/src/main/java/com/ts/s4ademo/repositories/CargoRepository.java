package com.ts.s4ademo.repositories;

import com.ts.s4ademo.entities.Cargo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CargoRepository {
    Optional<Cargo> findByFlightId(Long flightId);
}
