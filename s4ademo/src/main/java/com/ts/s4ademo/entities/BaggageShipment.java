package com.ts.s4ademo.entities;

import com.ts.s4ademo.utils.WeightUnit;
import lombok.*;

import javax.persistence.*;

@Table
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaggageShipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Integer weight;
    @Column
    private WeightUnit weightUnit;
    @Column
    private Integer pieces;

}
