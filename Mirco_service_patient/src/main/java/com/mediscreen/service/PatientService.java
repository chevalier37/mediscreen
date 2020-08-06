package com.mediscreen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.model.Patient;
import com.mediscreen.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	PatientRepository PatientRepository;

	public Patient addPatient(Patient patient) {
		return PatientRepository.save(patient);
	}

}
