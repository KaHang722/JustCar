package com.humber.justCar.controllers;

import com.humber.justCar.models.Car;
import com.humber.justCar.repositories.CarMakeRepository;
import com.humber.justCar.repositories.CarModelRepository;
import com.humber.justCar.repositories.CarYearRepository;
import com.humber.justCar.services.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
public class CarControllers implements EnvironmentAware {

    Environment environment;

    private CarService carService;

    private CarYearRepository carYearRepository;

    private CarMakeRepository carMakeRepository;
    private CarModelRepository carModelRepository;

    @Autowired
    public CarControllers(CarYearRepository carYearRepository, CarModelRepository carModelRepository, CarMakeRepository carMakeRepository, CarService carService){
        this.carYearRepository=carYearRepository;
        this.carModelRepository=carModelRepository;
        this.carMakeRepository=carMakeRepository;
        this.carService=carService;

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;

    }

    @GetMapping("/")
    public String homePage(Model m){
        m.addAttribute("appName", environment.getProperty("application.name"));
        return "home";
    }

    @GetMapping("/search/key/")
    public String searchCar(Model model){
        model.addAttribute("appName",environment.getProperty("application.name"));
        return "search";
    }

    @GetMapping("/inventory")
    public String inventory(Model m){
        m.addAttribute("cars", carService.getCars());
        m.addAttribute("appName", environment.getProperty("application.name"));
        return "inventory";
    }

    @GetMapping("/search")
    public String search(@RequestParam (required = false)   Integer carYear, @RequestParam (required = false) String make, @RequestParam (required = false) String model, @RequestParam (required = false) String transmission, Model m){
        if (carYear == null && (make == null || make.isEmpty()) && (model == null || model.isEmpty()) && (transmission == null || transmission.isEmpty())) {
            m.addAttribute("cars", carService.getCars());
        } else {
            if (carYear == null) {
                carYear = 0;
           }
            m.addAttribute("cars", carService.getCarByYearOrMakeOrModelOrTransmission(carYear, make, model, transmission));
        }
        m.addAttribute("appName", environment.getProperty("application.name"));
        return "inventory";
    }

    @GetMapping("/add/car")
    public String addCarPage(Model m){
        m.addAttribute("appName", environment.getProperty("application.name"));
        m.addAttribute("car", new Car());
        m.addAttribute("carYears", carYearRepository.findAll());
        m.addAttribute("makes", carMakeRepository.findAll());
        m.addAttribute("models", carModelRepository.findAll());
        return "add-car";
    }

    @PostMapping("/add/car")
    public String addCar(Model m, @Valid @ModelAttribute Car car, BindingResult result){
        if(result.hasErrors()){
            m.addAttribute("appName", environment.getProperty("application.name"));
            m.addAttribute("car", car);
            m.addAttribute("carYears", carYearRepository.findAll());
            m.addAttribute("makes", carMakeRepository.findAll());
            m.addAttribute("models", carModelRepository.findAll());
            if(!car.getVin().getVinNumber().isEmpty()) m.addAttribute("edit", true);
            return "add-car";
        }
        this.carService.addCar(car);
        return "redirect:/inventory";
    }

    @PostMapping("/delete/car/{vin}")
    public String deleteCar(Model m, @PathVariable String vin, RedirectAttributes redirectAttributes){
        carService.deleteCar(vin);
        redirectAttributes.addFlashAttribute("message", "Car deleted successfully.");
        return "redirect:/inventory";
    }

    @GetMapping("/edit/{vinnumber}")
    public String editCar (@PathVariable String vinnumber, Model m){
        Car car = this.carService.findCarByVinNumber(vinnumber).get();
        m.addAttribute("appName", environment.getProperty("application.name"));
        m.addAttribute("car", car);
        m.addAttribute("edit", true);
        m.addAttribute("carYears", carYearRepository.findAll());
        m.addAttribute("makes", carMakeRepository.findAll());
        m.addAttribute("models", carModelRepository.findAll());
        return "add-car";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler (NoHandlerFoundException.class)
    public String handel404(NoHandlerFoundException e, Model m){
        m.addAttribute("appName", environment.getProperty("application.name"));
        return "error";
    }

    @ExceptionHandler (NoSuchElementException.class)
    public String noSuchElementHandler(NoSuchElementException e, Model m){
        m.addAttribute("appName", environment.getProperty("application.name"));
        return "exception/noProductFound";
    }

    @RequestMapping("/notAuthorized.html")
    public String handel403(Model model) {
        model.addAttribute("appName", environment.getProperty("application.name"));
        return "notAuthorized";
    }

    @GetMapping("/justCarLogin")
    public String login(Model m){
        m.addAttribute("appName", environment.getProperty("application.name"));
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model m){
        m.addAttribute("appName", environment.getProperty("application.name"));
        return "redirect:/";
    }
}
