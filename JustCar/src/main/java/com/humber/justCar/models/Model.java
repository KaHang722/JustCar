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
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int modelId;

    @Size(max=10, min=3, message = "Model size must be between 3 and 10")
    private String carModel;
}
