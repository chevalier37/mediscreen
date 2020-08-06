package com.mediscreen.service;

import java.util.List;
import java.util.Optional;

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

	public List<Patient> listPatient() {
		return PatientRepository.findAll();
	}

	public Optional<Patient> getPatient(String id) {
		return PatientRepository.findById(id);
	}

}
