package com.mediscreen.controller;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mediscreen.model.Gender;
import com.mediscreen.model.Note;
import com.mediscreen.model.Patient;
import com.mediscreen.proxies.NoteProxy;
import com.mediscreen.proxies.RapportProxy;
import com.mediscreen.service.PatientService;

@Controller
@RequestMapping("/patient")
public class PatientController {

	String urlGateway = "http://localhost:8080";

	@Autowired
	PatientService patientService;

	@Autowired
	NoteProxy noteProxy;

	@Autowired
	RapportProxy rapportProxy;

	@GetMapping("/add")
	public String addPatient(Patient patient) {
		return "Patient/add";
	}

	@GetMapping("/list")
	public String listPatient(Model model) {
		List<Patient> patienList = patientService.listPatient();
		model.addAttribute("allPatient", patienList);
		return "Patient/list";
	}

	@PostMapping("/validate")
	public void validate(@Valid @ModelAttribute("patient") Patient patient, BindingResult result, Model model,
			HttpServletResponse servletResponse) throws ParseException, IOException {

		DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birth = LocalDate.now();
		String rUrl = urlGateway + "/patient/list";
		if (!result.getFieldValue("dateOfBirth").toString().isEmpty()) {
			birth = LocalDate.parse(result.getFieldValue("dateOfBirth").toString(), simpleDateFormat);
		}

		String firstName = result.getFieldValue("firstName").toString();
		String familyName = result.getFieldValue("familyName").toString();
		String gender = result.getFieldValue("gender").toString();
		String address = result.getFieldValue("address").toString();
		String phone = result.getFieldValue("phone").toString();
		Gender enumGender;
		if (gender.equals("F")) {
			enumGender = Gender.F;
		} else {
			enumGender = Gender.M;
		}

		Patient newPatient = new Patient(firstName, familyName, birth, enumGender, address, phone);

		if (!result.hasErrors()) {
			patientService.addPatient(newPatient);
			model.addAttribute("allPatient", patientService.listPatient());
			servletResponse.sendRedirect(rUrl);
			return;
		}

		servletResponse.sendRedirect(rUrl);
		return;

	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") String id, Model model) {

		Patient patient = patientService.getPatientById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));

		model.addAttribute("patient", patient);
		return "Patient/update";
	}

	@PostMapping("/update/{id}")
	public void updatePatient(@PathVariable("id") String id, @Valid Patient patient, BindingResult result, Model model,
			HttpServletResponse servletResponse) throws IOException {
		String rUrl = urlGateway + "/patient/list";
		if (!result.hasErrors()) {
			patientService.addPatient(patient);
			model.addAttribute("allPatient", patientService.listPatient());
			servletResponse.sendRedirect(rUrl);
			return;
		}
		servletResponse.sendRedirect(rUrl);
		return;
	}

	@GetMapping("/{id}")
	public String showPatient(@PathVariable("id") String id, Model model) {
		Patient patient = patientService.getPatientById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
		String rapport = rapportProxy.getRapport(id);
		model.addAttribute("rapport", rapport);
		model.addAttribute("patient", patient);
		return "Patient/showPatient";
	}

	@GetMapping("/note/{idPatient}")
	public String patientNotes(@PathVariable("idPatient") String idPatient, Model model) {
		List<Note> listNotes = noteProxy.listNotes(idPatient);
		Patient patient = patientService.getPatientById(idPatient)
				.orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + idPatient));
		model.addAttribute("patient", patient);
		model.addAttribute("notes", listNotes);
		return "Patient/listNotes";
	}

	@GetMapping("/addNotes/{idPatient}")
	public String addNoteview(@PathVariable("idPatient") String idPatient, Note note, Model model) {
		Patient patient = patientService.getPatientById(idPatient)
				.orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + idPatient));
		model.addAttribute("patient", patient);

		return "Patient/addNotes";
	}

	@PostMapping("/addNotes/{idPatient}")
	public void addNote(@ModelAttribute("note") Note note, BindingResult result, Model model,
			@PathVariable("idPatient") String idPatient, HttpServletResponse servletResponse)
			throws ParseException, IOException {

		String getNote = result.getFieldValue("note").toString();
		Note creatNote = new Note(idPatient, getNote, new Date());
		noteProxy.addNote(creatNote);

		model.addAttribute("allPatient", patientService.listPatient());

		String rUrl = urlGateway + "/patient/note/" + idPatient;
		servletResponse.sendRedirect(rUrl);

	}

	@GetMapping("/updateNote/{idNote}/{patientId}")
	public String showUpdateNote(@PathVariable("idNote") String idNote, @PathVariable("patientId") String patientId,
			Model model) {

		Note note = noteProxy.getNote(idNote);
		model.addAttribute("note", note);
		Patient patient = patientService.getPatientById(patientId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + patientId));
		model.addAttribute("patient", patient);
		return "Patient/updateNote";
	}

	@PostMapping("/updateNote/{id}")
	public void updateNote(@PathVariable("id") String id, Note note, BindingResult result, Model model,
			HttpServletResponse servletResponse) throws IOException {

		Note getNote = noteProxy.getNote(id);
		String noteText = result.getFieldValue("note").toString();
		getNote.setNote(noteText);
		noteProxy.addNote(getNote);
		String idPatient = getNote.getPatientId();

		model.addAttribute("allPatient", patientService.listPatient());

		String rUrl = urlGateway + "/patient/note/" + idPatient;
		servletResponse.sendRedirect(rUrl);

	}

}
