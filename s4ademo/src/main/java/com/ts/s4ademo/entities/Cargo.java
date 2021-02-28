package com.ts.s4ademo.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Builder
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long flightId;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "baggage_id")
    private List<BaggageShipment> baggage;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_id")
    private List<CargoShipment> cargo;
}
