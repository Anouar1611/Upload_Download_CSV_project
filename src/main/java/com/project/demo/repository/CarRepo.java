package com.project.demo.repository;


import com.project.demo.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepo extends JpaRepository<Car,Long> {

    //List<Car> findByFirstName(String firstName);
    List<Car> findCarByCar(String car);

}
