package com.ts.s4ademo.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AirportStats {

    private int departures;
    private int arrivals;
    private int arrivingBaggages;
    private int departingBaggages;

}
