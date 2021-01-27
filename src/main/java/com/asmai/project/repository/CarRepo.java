package com.asmai.project.repository;


import com.asmai.project.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepo extends JpaRepository<Car,Long> {

    //List<Car> findByFirstName(String firstName);
    List<Car> findCarByCar(String car);

}
