package com.humber.justCar.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carYearId;

    @Min(2000)
    @Max(2024)
    private int carYearValue;
}
