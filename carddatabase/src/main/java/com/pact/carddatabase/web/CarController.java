package com.pact.carddatabase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pact.carddatabase.domain.Car;
import com.pact.carddatabase.domain.CarRepository;

@RestController
public class CarController {
	
	@Autowired
	private CarRepository carRepo;

	@GetMapping("/cars")
	public Iterable<Car> getCars(){
		return carRepo.findAll();
	}
	
	@DeleteMapping("/cars/{id}")
	public void deleteCar(@PathVariable Long id) 
	{
		carRepo.deleteById(id);
	}
	
	@PostMapping("/addCar")
	public Car addCar(@RequestBody Car newCar) {
		if(newCar != null) {
			carRepo.save(newCar);
		}
		return newCar;
	}
	
	@PutMapping("/updateCar/{id}")
	public void updateCar(@PathVariable Long id, @RequestBody Car car) throws Exception {
		
		Car cr = carRepo.findById(id).orElseThrow(()-> new Exception("Car Not Found!!!"));
		
		cr.setBrand(car.getBrand());
		cr.setColor(car.getColor());
		cr.setRegisterNumber(car.getRegisterNumber());
		cr.setPrice(car.getPrice());
		cr.setYear(car.getYear());
		
		carRepo.save(cr);
	}
}
