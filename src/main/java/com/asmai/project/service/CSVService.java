package com.asmai.project.service;

import com.asmai.project.helper.CSVHelper;
import com.asmai.project.model.Car;
import com.asmai.project.repository.CarRepo;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CSVService {

    @Autowired
    CarRepo repository;

    @Autowired
    SparkSession sparkSession;

    public void save(MultipartFile file) {
        try {
            List<Car> Cars = CSVHelper.csvToCars(file.getInputStream());
            repository.saveAll(Cars);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<Car> getAllCars() {
        return repository.findAll();
    }

    public List<Car> getCarByName(String name){

        return repository.findAll().stream()
                .filter(s->s.getCar().contentEquals(name))
                .collect(Collectors.toList());
    }

    public List<Car> getCarsByName(String name) {

        return repository.findCarByCar(name);/*.stream()
                .filter(s->s.getCar().contentEquals(name))
                .collect(Collectors.toList());*/

    }

    public List<Car> getCarsByModel(String model){

        return repository.findAll().stream()
                .filter(s->s.getModel().contentEquals(model))
                .collect(Collectors.toList());

    }

    public Car getCarHaveLessHorsePower() {

        return repository.findAll().stream()
                .min(Comparator.comparing(Car::getHorsepower)).get();
    }


    public List<Car> sortedByModelAndHorsePower() {

        return repository.findAll().stream()
                .sorted(Comparator.comparing(Car::getModel).thenComparing(Car::getHorsepower))
                .collect(Collectors.toList());

    }

    public List<Car> getCarsByNameAndSortedByModel(String name) {

        return repository.findAll().stream()
                .filter(s->s.getModel().contentEquals(name))
                .sorted(Comparator.comparing(Car::getCar))
                .collect(Collectors.toList());

    }

    public List<Car> getCarsByModelAndOriginAndSortedByHorsePower(String origin, String model){

        return repository.findAll().stream()
                .filter(s->s.getOrigin().contentEquals(origin))
                .filter(s->s.getModel().contentEquals(model))
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());
    }



}
