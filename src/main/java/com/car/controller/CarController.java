package com.car.controller;

import com.car.exception.CarNotFoundException;
import com.car.model.Car;
import com.car.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/cars")
    public List<Car> retrieveAllCars(){
        return carRepository.findAllCars();
    }

    @GetMapping("/cars/{id}")
    public EntityModel<Car> retrieveCar(@PathVariable long id){
        Car car = new Car();
        try{
            car = carRepository.findByCarId(id);

        }catch (DataAccessException e) {
            if (car.getId() == 0) {
                throw new CarNotFoundException("Not found id - " + id);
            }
        }
        EntityModel<Car> carEntityModel = new EntityModel<>(car);
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllCars());
        carEntityModel.add(linkTo.withRel("all-cars"));

        return carEntityModel;
    }

    @PostMapping("/cars")
   public ResponseEntity<Object> createCar(@RequestBody Car car){
        int i = carRepository.insertCar(car);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(i).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/cars/update/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable(value = "id") long id, @RequestBody Car car)
    {
        Car carObj = carRepository.findByCarId(id);
        if(carObj.getId() == 0){
            return ResponseEntity.notFound().build();
        }

        carObj.setName(car.getName());
        carObj.setModel(car.getModel());
        carObj.setManufactureName(car.getManufactureName());
        carObj.setManufactureYear(car.getManufactureYear());
        carObj.setColor(car.getColor());
        carRepository.updateCar(carObj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/cars/delete/{id}")
    public void deleteCar(@PathVariable long id) {
        carRepository.deleteByCarId(id);
    }

    @PostMapping("/cars/search")
    public List<Car> dynamicSearch(@RequestBody Car car){
        return carRepository.dynamicSearch(car);
    }
}
