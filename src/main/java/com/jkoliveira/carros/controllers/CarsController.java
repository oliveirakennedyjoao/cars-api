package com.jkoliveira.carros.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkoliveira.carros.dtos.CarDTO;
import com.jkoliveira.carros.models.Car;
import com.jkoliveira.carros.repositories.CarRepository;

@RestController
@RequestMapping("/cars")
public class CarsController {
	
	@Autowired
	private CarRepository carRepository;
	
	@GetMapping
	public List<Car> list() {
		return carRepository.findAll();
	}
	
	@PostMapping
	public void save(@RequestBody CarDTO car) {
		carRepository.save(new Car());
	}
	
	@PutMapping
	public void update(@RequestBody CarDTO car) {
		carRepository.save(new Car());
	}
	
	@DeleteMapping
	public void delete(@RequestBody CarDTO car) {
		carRepository.save(new Car());
	}
}
