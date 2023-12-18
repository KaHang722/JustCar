package com.humber.justCar.repositories;

import com.humber.justCar.models.Make;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarMakeRepository extends CrudRepository<Make, String> {
}
