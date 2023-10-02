package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Medicine;
import com.example.demo.repo.MedicineRepo;

@RestController
public class MedicineController {

	@Autowired
	private MedicineRepo medicineRepo;// Repository for performing CRUD operations in medicines table
	
	
	// end point for editing the medicine details
	
	@PutMapping("/editMedicine")
	public Medicine edit(@RequestBody Medicine medicine) {
		
		medicineRepo.save(medicine);
		
		return medicine;
		
	}
	
}
