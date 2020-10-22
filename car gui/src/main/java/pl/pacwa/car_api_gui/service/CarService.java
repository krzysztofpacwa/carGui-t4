package pl.pacwa.car_api_gui.service;

import pl.pacwa.car_api_gui.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getCarsAll();
    Optional<Car> getCarId(long id);
    List<Car> getCarsColor(String color);
    boolean addCar(Car car);
    boolean updateAllCar(Car updateCar);
    boolean deleteCar(long id);

}
