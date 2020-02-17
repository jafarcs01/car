package com.car.repository;

import com.car.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CarRepositoryimpl implements CarRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Car> findAllCars() {
        return jdbcTemplate.query("select * from car",  new BeanPropertyRowMapper<Car>(Car.class));
    }

    @Override
    public Car findByCarId(long id) {
        return jdbcTemplate.queryForObject("select * from car where id=?", new Object[]{id}, new BeanPropertyRowMapper<Car>(Car.class));
    }

    @Override
    public int deleteByCarId(long id) {
        return jdbcTemplate.update("delete from car where id=?", new Object[]{
                id
        });
    }

    @Override
    public int insertCar(Car car) {
        return jdbcTemplate.update("insert into car(id, name, manufactureName, model, manufactureyear, color)"+ "values (?,?,?,?,?,?)",
        new Object[]{
                car.getId(), car.getName(), car.getManufactureName(), car.getModel(), car.getManufactureYear(), car.getModel()
        });
    }

    @Override
    public int updateCar(Car car) {
        return jdbcTemplate.update("update car "+" set name=?, manufactureName=?, model=?, manufactureyear=?, color=?" + " where id=?",
                new Object[]{
                        car.getName(), car.getManufactureName(), car.getModel(), car.getManufactureYear(), car.getColor(), car.getId()
                });
    }

    public List<Car> dynamicSearch(Car car){
        String dynamicQuery = GenerateQuery.generateWhereClause(car);
        return jdbcTemplate.query(dynamicQuery,  new BeanPropertyRowMapper<Car>(Car.class));
    }
}
