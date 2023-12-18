package com.humber.justCar;

import com.humber.justCar.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JustCar implements CommandLineRunner {

    @Autowired
    private CarService carService;

    public static void main(String[] args) {
        SpringApplication.run(JustCar.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.carService.addCarDataToDB();
    }
}
