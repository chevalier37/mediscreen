package com.mediscreen;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mediscreen.controller.PatientController;
import com.mediscreen.proxies.NoteProxy;
import com.mediscreen.proxies.RapportProxy;
import com.mediscreen.service.PatientService;

@WebMvcTest(PatientController.class)
@ExtendWith(SpringExtension.class)
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	PatientService patientService;

	@MockBean
	NoteProxy noteProxy;

	@MockBean
	RapportProxy rapportProxy;

	@Test
	public void testCorrectModel() {
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/patient/list")).andExpect(status().isOk())
					.andExpect(model().attributeExists("allPatient")).andExpect(view().name("Patient/list"));
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void validate() {
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.post("/patient/validate")).andExpect(status().isOk())
					.andExpect(model().attributeExists("allPatient"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addPatient() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/patient/add")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void listPatient() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/patient/list")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void update() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/patient/update/{id}", "1")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void showPatient() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/patient/update/1")).andExpect(status().isOk());
	}

}
