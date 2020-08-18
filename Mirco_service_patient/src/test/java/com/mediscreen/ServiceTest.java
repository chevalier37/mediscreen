package com.mediscreen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mediscreen.model.Gender;
import com.mediscreen.model.Patient;
import com.mediscreen.service.PatientService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ServiceTest {

	@Autowired
	private PatientService patientService;

	@Test
	@Sql({ "/mediscreenTest.sql" })
	public void addPatientTest() {
		byte[] array = new byte[7];
		new Random().nextBytes(array);
		Patient patient = new Patient("test", "familyNane", LocalDate.now(), Gender.M, "150 street", "120-120-120");
		patientService.addPatient(patient);

		Patient getPatient = patientService.getPatientByFamilyName("familyNane");
		assertEquals(getPatient.getFamilyName(), "familyNane");
	};

	@Test
	@Sql({ "/mediscreenTest.sql" })
	public void getPatientByIdTest() {
		byte[] array = new byte[7];
		new Random().nextBytes(array);
		Patient patient = new Patient("test", "familyNane", LocalDate.now(), Gender.M, "150 street", "120-120-120");
		patientService.addPatient(patient);

		Patient getPatient = patientService.getPatientByFamilyName("familyNane");
		getPatient.setAddress("007 street");
		patientService.addPatient(getPatient);
		assertEquals(getPatient.getAddress(), "007 street");
	};

}
