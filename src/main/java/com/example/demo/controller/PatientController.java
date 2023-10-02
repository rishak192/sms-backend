package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Patient;
import com.example.demo.repo.PatientRepo;

@RestController
public class PatientController {
		
	@Autowired
	private PatientRepo patientRepo; // repository for performing CRUD operations in Patient table
	
	// end point for adding a patient in patient table
	
	@PostMapping("/addPatient")
	public Patient add(@RequestBody Patient patient) {
		
		patientRepo.save(patient);
		
		return patient;
		
	}
	
}
