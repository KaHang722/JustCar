package com.humber.justCar.repositories;

import com.humber.justCar.models.Model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelRepository extends CrudRepository<Model, String> {
}
