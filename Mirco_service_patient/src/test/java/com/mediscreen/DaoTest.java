package com.mediscreen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.mediscreen.model.Gender;
import com.mediscreen.model.Patient;
import com.mediscreen.repository.PatientRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {

	@Autowired
	private PatientRepository patientRepository;

	@Test
	@Sql({ "/mediscreenTest.sql" })
	public void addTest() {
		byte[] array = new byte[7];
		new Random().nextBytes(array);
		Patient patient = new Patient("test", "familyNane", LocalDate.now(), Gender.M, "150 street", "120-120-120");
		patientRepository.save(patient);

		Patient getPatient = patientRepository.findByFamilyName("familyNane");
		assertEquals(getPatient.getFamilyName(), "familyNane");
	};

	@Test
	@Sql({ "/mediscreenTest.sql" })
	public void updateTest() {
		byte[] array = new byte[7];
		new Random().nextBytes(array);
		Patient patient = new Patient("test", "familyNane1", LocalDate.now(), Gender.M, "150 street", "120-120-120");
		patientRepository.save(patient);

		Patient getPatient = patientRepository.findByFamilyName("familyNane1");
		getPatient.setAddress("007 street");
		patientRepository.save(getPatient);
		assertEquals(getPatient.getAddress(), "007 street");
	};

}
