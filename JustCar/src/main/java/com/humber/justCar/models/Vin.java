package com.humber.justCar.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Separate class same table
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vin {

    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int vinId;

    private String vinNumber;
}
