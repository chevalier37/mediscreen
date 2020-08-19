package com.mediscreen;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.mediscreen.controller.PatientController;
import com.mediscreen.model.Gender;
import com.mediscreen.model.Note;
import com.mediscreen.model.Patient;
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
	public void listTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/patient/list")).andExpect(status().isOk())
				.andExpect(model().attributeExists("allPatient")).andExpect(view().name("Patient/list"));
	}

	@Test
	public void validateTest() throws Exception {
		Patient patient = new Patient("test12", "familyNane", LocalDate.now(), Gender.M, "150 street", "120-120-120");
		mockMvc.perform(post("/patient/validate").flashAttr("patient", patient)).andExpect(status().isFound());
	}

	@Test
	public void addPatient() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/patient/add")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void update() throws Exception {
		Patient patient = new Patient("test12", "familyNane", LocalDate.now(), Gender.M, "150 street", "120-120-120");
		Mockito.when(patientService.getPatientById(1)).thenReturn(Optional.of(patient));
		this.mockMvc.perform(MockMvcRequestBuilders.get("/patient/update/1").accept(MediaType.TEXT_PLAIN))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void showPatient() throws Exception {
		Patient patient = new Patient("test12", "familyNane", LocalDate.now(), Gender.M, "150 street", "120-120-120");
		Mockito.when(patientService.getPatientById(1)).thenReturn(Optional.of(patient));
		this.mockMvc.perform(MockMvcRequestBuilders.get("/patient/{id}", "1")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void patientNotesTest() throws Exception {
		Patient patient = new Patient("test12", "familyNane", LocalDate.now(), Gender.M, "150 street", "120-120-120");
		Mockito.when(patientService.getPatientById(1)).thenReturn(Optional.of(patient));
		this.mockMvc.perform(MockMvcRequestBuilders.get("/patient/note/{idPatient}", 1)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void addNotesTest() throws Exception {
		Patient patient = new Patient("test12", "familyNane", LocalDate.now(), Gender.M, "150 street", "120-120-120");
		Mockito.when(patientService.getPatientById(1)).thenReturn(Optional.of(patient));
		this.mockMvc.perform(MockMvcRequestBuilders.get("/patient/addNotes/1").accept(MediaType.TEXT_PLAIN))
				.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	public void updateNoteTest() throws Exception {
		Patient patient = new Patient("test12", "familyNane", LocalDate.now(), Gender.M, "150 street", "120-120-120");
		Mockito.when(patientService.getPatientById(1)).thenReturn(Optional.of(patient));
		this.mockMvc.perform(MockMvcRequestBuilders.post("/patient/updateNote/1")).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void addNoteTest() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/patient/addNotes/1").contentType(MediaType.APPLICATION_JSON)
				.content("{\"patientId\": \"50\",\"note\": \"controller test\",\"date\": \"2012-04-23T18:25:43.511Z\"}")
				.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}

	@Test
	public void updatePatientTest() throws Exception {
		Patient patient = new Patient("test12", "familyNane", LocalDate.now(), Gender.M, "150 street", "120-120-120");
		Patient patient1 = new Patient("test12", "familyNane", LocalDate.now(), Gender.M, "150 street", "120-120-120");
		Note note = new Note(1, "note", LocalDate.now());
		List<Patient> listPatient = new ArrayList<>();
		listPatient.add(patient1);
		listPatient.add(patient);
		mockMvc.perform(post("/patient/update/1").flashAttr("allPatient", listPatient).flashAttr("note", note))
				.andExpect(status().isFound());
	}

	@Test
	public void showUpdateNoteTest() throws Exception {
		Patient patient = new Patient("test12", "familyNane", LocalDate.now(), Gender.M, "150 street", "120-120-120");
		Note note = new Note(1, "note", LocalDate.now());
		note.setId("123");
		Mockito.when(patientService.getPatientById(1)).thenReturn(Optional.of(patient));
		mockMvc.perform(get("/patient/updateNote/5f3bcbe960fcf35d75eba620/1").flashAttr("patient", patient)
				.flashAttr("note", note)).andExpect(status().isFound());
	}

}
