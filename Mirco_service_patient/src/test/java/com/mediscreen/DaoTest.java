package com.mediscreen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mediscreen.model.Patient;
import com.mediscreen.repository.PatientRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {

	@Autowired
	private PatientRepository patientRepository;

	@Test
	public void addTest() {
		byte[] array = new byte[7];
		new Random().nextBytes(array);
		String familyNane = new String(array, Charset.forName("UTF-8"));
		Patient patient = new Patient("test", familyNane, new Date(), "M", "150 street", "120-120-120");
		patientRepository.save(patient);

		Patient getPatient = patientRepository.findByFamilyName(familyNane);
		assertEquals(getPatient.getFamilyName(), familyNane);
	};

	@Test
	public void updateTest() {
		byte[] array = new byte[7];
		new Random().nextBytes(array);
		String familyNane = new String(array, Charset.forName("UTF-8"));
		Patient patient = new Patient("test", familyNane, new Date(), "M", "150 street", "120-120-120");
		patientRepository.save(patient);

		Patient getPatient = patientRepository.findByFamilyName(familyNane);
		getPatient.setAddress("007 street");
		patientRepository.save(getPatient);
		assertEquals(getPatient.getAddress(), "007 street");
	};

}
