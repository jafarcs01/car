package com.car.model;

import java.io.Serializable;

public class Car implements Serializable {

    private long id;
    private String name;
    private String manufactureName;
    private String model;
    private String manufactureYear;
    private String color;

    public Car() {
    }

    public Car(long id, String name, String manufactureName, String model, String manufactureYear, String color) {
        this.id = id;
        this.name = name;
        this.manufactureName = manufactureName;
        this.model = model;
        this.manufactureYear = manufactureYear;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufactureName() {
        return manufactureName;
    }

    public void setManufactureName(String manufactureName) {
        this.manufactureName = manufactureName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(String manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
