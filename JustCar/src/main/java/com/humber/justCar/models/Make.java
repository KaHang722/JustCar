package com.humber.justCar.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Make {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int makeId;

    @Size(max=20, min=3, message = "Make must be between 3 and 20")
    private String carMake;
}
