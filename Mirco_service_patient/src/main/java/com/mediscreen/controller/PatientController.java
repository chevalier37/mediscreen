package com.mediscreen.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mediscreen.model.Patient;
import com.mediscreen.service.PatientService;

@Controller
public class PatientController {

	@Autowired
	PatientService PatientService;

	@GetMapping("addPatient")
	public String addPatient(Patient patient) {
		return "Patient/add";
	}

	@PostMapping("/patient/validate")
	public String validate(@Valid @ModelAttribute("patient") Patient patient, BindingResult result, Model model)
			throws ParseException {

		DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date birth = new Date();
		if (!result.getFieldValue("dateOfBirth").toString().isEmpty()) {
			birth = simpleDateFormat.parse(result.getFieldValue("dateOfBirth").toString());
		}
		String firstName = result.getFieldValue("firstName").toString();

		String familyName = result.getFieldValue("familyName").toString();

		String gender = result.getFieldValue("gender").toString();

		String address = result.getFieldValue("address").toString();

		String phone = result.getFieldValue("phone").toString();

		Patient newPatient = new Patient(firstName, familyName, birth, gender, address, phone);

		if (!result.hasErrors()) {
			PatientService.addPatient(newPatient);
			// model.addAttribute("allBidList", bidListService.findAllBidList());
			// return "redirect:/bidList/list";
		}

		return "Patient/add";

	}

}
