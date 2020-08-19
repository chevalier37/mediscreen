package com.mediscreen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mediscreen.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	@Query("SELECT t FROM Patient t where t.familyName = :familyName")
	Patient findByFamilyName(String familyName);

}
