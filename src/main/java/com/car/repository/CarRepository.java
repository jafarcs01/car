package com.car.repository;

import com.car.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository {

    List<Car> findAllCars();
    Car findByCarId(long id);
    int deleteByCarId(long id);
    int insertCar(Car car);
    int updateCar(Car car);
    List<Car> dynamicSearch(Car car);
}
