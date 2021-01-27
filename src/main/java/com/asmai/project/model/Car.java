package com.asmai.project.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String car;

    private String MPG;

    private String Cylinders;

    private String Displacement;

    private String Horsepower;

    public Car(String car, String MPG, String cylinders, String displacement, String horsepower, String weight, String acceleration, String model, String origin) {
        this.car = car;
        this.MPG = MPG;
        Cylinders = cylinders;
        Displacement = displacement;
        Horsepower = horsepower;
        Weight = weight;
        Acceleration = acceleration;
        Model = model;
        Origin = origin;
    }

    private String Weight;

    private String Acceleration;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getMPG() {
        return MPG;
    }

    public void setMPG(String MPG) {
        this.MPG = MPG;
    }

    public String getCylinders() {
        return Cylinders;
    }

    public void setCylinders(String cylinders) {
        Cylinders = cylinders;
    }

    public String getDisplacement() {
        return Displacement;
    }

    public void setDisplacement(String displacement) {
        Displacement = displacement;
    }

    public String getHorsepower() {
        return Horsepower;
    }

    public void setHorsepower(String horsepower) {
        Horsepower = horsepower;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getAcceleration() {
        return Acceleration;
    }

    public void setAcceleration(String acceleration) {
        Acceleration = acceleration;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    private String Model;

    private String Origin;


    public Car(){

    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", car='" + car + '\'' +
                ", MPG='" + MPG + '\'' +
                ", Cylinders='" + Cylinders + '\'' +
                ", Displacement='" + Displacement + '\'' +
                ", Horsepower='" + Horsepower + '\'' +
                ", Weight='" + Weight + '\'' +
                ", Acceleration='" + Acceleration + '\'' +
                ", Model='" + Model + '\'' +
                ", Origin='" + Origin + '\'' +
                '}';
    }
}
