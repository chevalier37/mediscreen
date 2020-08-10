package com.mediscreen;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mediscreen.controller.PatientController;
import com.mediscreen.service.PatientService;

@WebMvcTest(PatientController.class)
@ExtendWith(SpringExtension.class)
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PatientService mockService;

	@MockBean
	private MongoTemplate mongoTemplate;

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
		this.mockMvc.perform(MockMvcRequestBuilders.get("/patient/update/{id}", "5f2c011a8939bf0193f92614"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void showPatient() throws Exception {
		this.mockMvc.perform(get("/patient/update/5f2c011a8939bf0193f92614")).andExpect(status().isOk());
	}

}
