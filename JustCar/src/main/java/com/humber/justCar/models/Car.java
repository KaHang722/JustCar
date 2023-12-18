package com.humber.justCar.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "CARS")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Embedded
    @NotNull
    //One to One Relationship
    //@OnetoOne
    private Vin vin;

    //One Car can have one make, One make can have many cars
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Make make;

    //One Car can have one model, One model can have many cars
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Model model;


    //One Car can have one carYear, One carYear can have many cars
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private CarYear carYear;

    private String transmission;

    private String image;
}
