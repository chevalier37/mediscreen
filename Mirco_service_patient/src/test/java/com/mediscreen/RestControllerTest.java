package com.mediscreen;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mediscreen.controller.RestPatientController;
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
		this.mockMvc.perform(MockMvcRequestBuilders.get("/patient/getPatient/1")).andDo(print())
				.andExpect(status().isNotFound());
	}

}
