package com.jkoliveira.carros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jkoliveira.carros.models.Car;

public interface CarRepository extends JpaRepository<Car, Long>{

}
