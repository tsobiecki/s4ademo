package com.ts.s4ademo.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Getter
@Setter
@Builder
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long flightId;
    @Column
    private Integer flightNumber;
    @Column
    private IATACode departureAirportIATACode;
    @Column
    private IATACode arrivalAirportIATACode;
    @Column
    private Date departureDate;

    public void setDepartureDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss X");
        try {
            this.departureDate = new Date(formatter.parse(date).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public enum IATACode {
        SEA, YYZ, YYT, ANC, LAX, MIT, LEW, GDN, KRK, PPX
    }

}
