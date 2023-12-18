package com.humber.justCar.repositories;

import com.humber.justCar.models.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car,String> {

    List<Car> findCarByCarYearCarYearValueOrMakeCarMakeOrModelCarModelOrTransmission(int year, String make, String model, String transmission);

    Optional<Car> findCarByVinVinNumber(String vin_vinNumber);

    @Query("select c from Car c " +
            "where STR(c.carYear.carYearValue) like %:search% or c.make.carMake like %:search% or c.model.carModel like %:search% or c.transmission like %:search%")
    List<Car> customSearch(String search);
}
