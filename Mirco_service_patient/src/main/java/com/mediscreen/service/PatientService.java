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
	PatientRepository patientRepository;

	public Patient addPatient(Patient patient) {
		return patientRepository.save(patient);
	}

	public List<Patient> listPatient() {
		return patientRepository.findAll();
	}

	public Optional<Patient> getPatientById(String id) {
		return patientRepository.findById(id);
	}

	public Patient getPatientByFamilyName(String familyName) {
		return patientRepository.findByFamilyName(familyName);
	}

}
