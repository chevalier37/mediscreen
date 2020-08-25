package com.mediscreen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.model.Patient;
import com.mediscreen.service.PatientService;

@RestController
@RequestMapping("/patient")
public class RestPatientController {

	@Autowired
	PatientService patientService;

	@GetMapping("getPatient/{id}")
	public Patient showPatient(@PathVariable("id") int id) {
		return patientService.getPatientById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
	}

	@PostMapping("/addpatient")
	public Patient validate(@RequestBody Patient patient) {
		patientService.addPatient(patient);
		return patient;

	}

	@PutMapping("/updatePatient")
	public Patient updatePatient(@RequestBody Patient patient) {
		patientService.addPatient(patient);
		return patient;
	}

}
