package com.humber.justCar.restcontrollers;

import com.humber.justCar.models.Car;
import com.humber.justCar.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CarRestController {

    private CarRepository carRepository;

    @Autowired
    public CarRestController(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @GetMapping("search/{searchParam}")
    public List<Car> searchCustom (@PathVariable String searchParam){
        return this.carRepository.customSearch(searchParam);
    }
}
