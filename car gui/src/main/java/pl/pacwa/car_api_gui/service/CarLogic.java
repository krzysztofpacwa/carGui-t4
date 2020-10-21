package pl.pacwa.car_api_gui.service;

import org.springframework.stereotype.Service;
import pl.pacwa.car_api_gui.model.Car;
import pl.pacwa.car_api_gui.model.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarLogic implements CarService {

    private List<Car> carList;

    public CarLogic(){

        carList = new ArrayList<>();
        carList.add(new Car(1L, "Mazda", "323F",Color.BLACK));
        carList.add(new Car(this.getLastId() + 1, "Ford", "Fiesta", Color.GOLDEN));
        carList.add(new Car(this.getLastId() + 1, "Ford", "Mustang", Color.BLACK));
        carList.add(new Car(this.getLastId() + 1, "Skoda", "Superb", Color.WHITE));
        carList.add(new Car(this.getLastId() + 1, "Alfa", "Stevio", Color.RED));
        carList.add(new Car(this.getLastId() + 1, "Nisan", "Quskai", Color.WHITE));
        carList.add(new Car(this.getLastId() + 1, "Opel", "Corsa", Color.GRIN));
    }

    private long getLastId() {
        return carList.get(carList.size() -1 ).getId();
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    @Override
    public List<Car> getCarsAll() {
        return carList;
    }

    @Override
    public Optional<Car> getCarId(long id) {
        Optional<Car> idCar = carList.stream().filter(car -> car.getId() == id)
                .findFirst();
        return idCar;
    }

    @Override
    public List<Car> getCarsColor(String color) {
        List<Car> colorCar = carList.stream().filter(car -> color.equalsIgnoreCase(car.getColor()
                .name()))
                .collect(Collectors.toList());
        return colorCar;
    }

    @Override
    public boolean addCar(Car car) {
        car.setId(this.getLastId() + 1 );
        return carList.add(car);
    }

    @Override
    public boolean updateAllCar(Car updateCar) {
        Optional<Car> ucar = carList.stream().filter(car -> car.getId() == updateCar.getId()).findFirst();
        ucar.filter(car -> !updateCar.getMark().equals("")).ifPresent(car -> car.setMark(updateCar.getMark()));
        ucar.filter(car -> !updateCar.getModel().equals("")).ifPresent(car -> car.setModel(updateCar.getModel()));
        ucar.filter(car -> !updateCar.getColor().equals("")).ifPresent(car -> car.setColor(updateCar.getColor()));
        return ucar.isPresent();
    }

    @Override
    public boolean deleteCar(long id) {
        Optional<Car> dcar = carList.stream().filter(car -> car.getId() == id).findFirst();
        return dcar.map(car -> carList.remove(car)).orElse(false);
    }
}
