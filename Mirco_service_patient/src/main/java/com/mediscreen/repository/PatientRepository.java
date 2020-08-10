package com.mediscreen.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mediscreen.model.Patient;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {

	@Query("{ 'familyName' : ?0 }")
	Patient findByFamilyName(String familyName);
}
