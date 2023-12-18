package com.humber.justCar.services;

import com.humber.justCar.models.*;
import com.humber.justCar.repositories.CarMakeRepository;
import com.humber.justCar.repositories.CarModelRepository;
import com.humber.justCar.repositories.CarRepository;
import com.humber.justCar.repositories.CarYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    private CarYearRepository carYearRepository;

    private CarMakeRepository carMakeRepository;

    private CarModelRepository carModelRepository;

    public CarService(CarRepository carRepository, CarYearRepository carYearRepository, CarMakeRepository carMakeRepository, CarModelRepository carModelRepository){
        this.carRepository=carRepository;
        this.carYearRepository = carYearRepository;
        this.carMakeRepository = carMakeRepository;
        this.carModelRepository = carModelRepository;
    }
    public List<Car> getAllCars(){
        //this.saveAllYears();
        //this.saveAllMakes();
        //this.saveAllModels();
        Car car1 = Car.builder()
                .model(Model.builder().carModel("Rubicon").build())
                .make(Make.builder().carMake("Jeep").build())
                .transmission("AUTOMATIC")
                .vin(Vin.builder().vinNumber("1234").build())
                .carYear(CarYear.builder().carYearValue(2000).build())
                .image("https://img.sm360.ca/ir/w640h390c/images/newcar/ca/2022/jeep/wrangler-4xe/unlimited-rubicon/suv/exteriorColors/2022_jeep_wrangler-4xe_unlimited-rubicon_032_pw7.png")
                .build();

       Car car2 = Car.builder()
               .model(Model.builder().carModel("Civic").build())
               .make(Make.builder().carMake("Honda").build())
               .transmission("AUTOMATIC")
               .vin(Vin.builder().vinNumber("1321").build())
               .carYear(CarYear.builder().carYearValue(2001).build())
               .image("https://www.motortrend.com/uploads/sites/10/2018/12/2019-honda-civic-lx-sedan-angular-front.png?fit=around%7C875:492.1875")
               .build();

        Car car3 = Car.builder()
                .model(Model.builder().carModel("Elantra").build())
                .make(Make.builder().carMake("Hyundai").build())
                .transmission("MANUAL")
                .vin(Vin.builder().vinNumber("2342").build())
                .carYear(CarYear.builder().carYearValue(2002).build())
                .image("https://images.dealer.com/ddc/vehicles/2022/Hyundai/Elantra/Sedan/perspective/front-left/2022_24.png")
                .build();

        Car car4 = Car.builder()
                .model(Model.builder().carModel("Model X").build())
                .make(Make.builder().carMake("Tesla").build())
                .transmission("AUTOMATIC")
                .vin(Vin.builder().vinNumber("4543").build())
                .carYear(CarYear.builder().carYearValue(2003).build())
                .image("https://d2ivfcfbdvj3sm.cloudfront.net/7fc965ab77efe6e0fa62e4ca1ea7673bb65a4058021e3d8e88cb/stills_0640_png/MY2022/50199/50199_st0640_116.png")
               .build();

        return Arrays.asList(car1, car2, car3, car4);
    }

    public List<Car> getCars(){
        return (List<Car>) this.carRepository.findAll();
    }

    public void addCarDataToDB(){
        this.carRepository.saveAll(this.getAllCars());
    }

    public List<Car> getCarByYearOrMakeOrModelOrTransmission(int year, String make, String model, String transmission){
        return this.carRepository.findCarByCarYearCarYearValueOrMakeCarMakeOrModelCarModelOrTransmission(year, make, model, transmission);
    }

    public void addCar(Car car){
        this.carRepository.save(car);
    }

    public Optional<Car> findCarByVinNumber(String vin_vinNumber){
        return this.carRepository.findCarByVinVinNumber(vin_vinNumber);
    }

    public void deleteCar(String vin){
        carRepository.deleteById(vin);
    }

    public void saveAllYears(){
        this.carYearRepository.saveAll(
                Arrays.asList(
                        CarYear.builder()
                                .carYearValue(2000)
                                .build(),
                        CarYear.builder()
                                .carYearValue(2001)
                                .build(),
                        CarYear.builder()
                                .carYearValue(2002)
                                .build(),
                        CarYear.builder()
                                .carYearValue(2003)
                                .build(),
                        CarYear.builder()
                                .carYearValue(2004)
                                .build(),
                        CarYear.builder()
                                .carYearValue(2005)
                                .build()
                )
        );
    }

    public void saveAllMakes(){
        this.carMakeRepository.saveAll(
                Arrays.asList(
                        Make.builder()
                                .carMake("Jeep")
                                .build(),
                        Make.builder()
                                .carMake("Honda")
                                .build(),
                        Make.builder()
                                .carMake("Hyundai")
                                .build(),
                        Make.builder()
                                .carMake("Tesla")
                                .build()
                )
        );
    }

    public void saveAllModels(){
        this.carModelRepository.saveAll(
                Arrays.asList(
                        Model.builder()
                                .carModel("Rubicon")
                                .build(),
                        Model.builder()
                                .carModel("Civic")
                                .build(),
                        Model.builder()
                                .carModel("Elantra")
                                .build(),
                        Model.builder()
                                .carModel("Model X")
                                .build()
                )
        );
    }
}
