package com.ts.s4ademo.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Weight {
    public static final Double LB_TO_KG = 0.45359237;

    public Double cargoWeight;
    public Double baggageWeight;
    public Double totalWeight;
}
