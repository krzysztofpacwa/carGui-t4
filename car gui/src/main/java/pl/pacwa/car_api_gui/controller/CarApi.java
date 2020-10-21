package pl.pacwa.car_api_gui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pacwa.car_api_gui.model.Car;
import pl.pacwa.car_api_gui.service.CarLogic;

@Controller
@RequestMapping("/")
public class CarApi {

    private CarLogic carLogic;

    @Autowired
    public CarApi(CarLogic carLogic) {
        this.carLogic = carLogic;
    }

    @GetMapping
    public String home(Model model){
        model.addAttribute("cars", carLogic.getCarsAll());
        model.addAttribute("newCars",new Car());
        model.addAttribute("editCars",new Car());
        return "index";

    }
    @PostMapping("/add-car")
    public String addCar(@ModelAttribute Car newCar){
        carLogic.addCar(newCar);
        return "redirect:/";
    }

    @PostMapping("edit-car")
    public String updateAllCar(@ModelAttribute Car updateAllCar){
        if (carLogic.updateAllCar(updateAllCar)){
            return "redirect:/";
        }
        return "error";
    }

    @PostMapping("/delete-car")
    public String deleteCar(@RequestParam long id){
        carLogic.deleteCar(id);
        return "redirect:/";

    }


}
