package com.mediscreen;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mediscreen.controller.RestPatientController;
import com.mediscreen.model.Gender;
import com.mediscreen.model.Patient;
import com.mediscreen.proxies.NoteProxy;
import com.mediscreen.proxies.RapportProxy;
import com.mediscreen.service.PatientService;

@WebMvcTest(RestPatientController.class)
@ExtendWith(SpringExtension.class)
public class RestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	PatientService patientService;

	@MockBean
	NoteProxy noteProxy;

	@MockBean
	RapportProxy rapportProxy;

	@Test
	public void showPatient() throws Exception {
		Patient patient = new Patient("test12", "familyNane", LocalDate.now(), Gender.M, "150 street", "120-120-120");
		Mockito.when(patientService.getPatientById(1)).thenReturn(Optional.of(patient));
		this.mockMvc.perform(MockMvcRequestBuilders.get("/patient/getPatient/1")).andDo(print())
				.andExpect(status().isOk());
	}

}
