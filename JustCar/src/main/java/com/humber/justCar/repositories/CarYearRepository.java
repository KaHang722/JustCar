package com.humber.justCar.repositories;

import com.humber.justCar.models.CarYear;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarYearRepository extends CrudRepository<CarYear, Integer> {
}
