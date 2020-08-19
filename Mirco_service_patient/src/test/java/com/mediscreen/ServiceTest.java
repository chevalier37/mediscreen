package com.mediscreen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
		Patient patient = new Patient("test13", "familyNane", LocalDate.now(), Gender.M, "150 street", "120-120-120");
		patientService.addPatient(patient);

		Patient getPatient = patientService.getPatientByFamilyName("familyNane");
		assertEquals(getPatient.getFamilyName(), "familyNane");
	};

	@Test
	@Sql({ "/mediscreenTest.sql" })
	public void getPatientByFamilyNameTest() {
		Patient patient = new Patient("test12", "familyNane", LocalDate.now(), Gender.M, "150 street", "120-120-120");
		patientService.addPatient(patient);

		Patient getPatient = patientService.getPatientByFamilyName("familyNane");
		getPatient.setAddress("007 street");
		patientService.addPatient(getPatient);
		assertEquals(getPatient.getAddress(), "007 street");
	};

	@Test
	@Sql({ "/mediscreenTest.sql" })
	public void listPatientTest() {
		Patient patient = new Patient("test14", "familyNane", LocalDate.now(), Gender.M, "150 street", "120-120-120");
		Patient patient1 = new Patient("test1", "familyNane1", LocalDate.now(), Gender.M, "150 street", "120-120-120");

		patientService.addPatient(patient);
		patientService.addPatient(patient1);

		List<Patient> getPatientList = patientService.listPatient();
		assertEquals(getPatientList.size(), 2);
	};

	@Test
	@Sql({ "/mediscreenTest.sql" })
	public void getPatientByFIdTest() {

		Patient patient = new Patient("test11", "familyNane", LocalDate.now(), Gender.M, "150 street", "120-120-120");
		patientService.addPatient(patient);

		Optional<Patient> getPatient = patientService.getPatientById(1);

		assertEquals(getPatient.get().getAddress(), "150 street");
	};

}
