package com.mediscreen.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "Patient")
public class Patient {

	@Id
	private String id;

	@Field(value = "firstName")
	@NotBlank(message = "First Name is mandatory")
	private String firstName;

	@NotBlank(message = "Family Name is mandatory")
	@Field(value = "familyName")
	private String familyName;

	@NotNull(message = "Date of birth is mandatory")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Field(value = "dateOfBirth")
	private Date dateOfBirth;

	@NotBlank(message = "Gender is mandatory")
	@Field(value = "gender")
	private String gender;

	@Field(value = "address")
	private String address;

	@Field(value = "phone")
	private String phone;

	public Patient(@NotBlank(message = "First Name is mandatory") String firstName,
			@NotBlank(message = "Family Name is mandatory") String familyName,
			@NotBlank(message = "Date of birth is mandatory") Date dateOfBirth,
			@NotBlank(message = "Gender is mandatory") String gender, String address, String phone) {
		this.firstName = firstName;
		this.familyName = familyName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", firstName=" + firstName + ", familyName=" + familyName + ", dateOfBirth="
				+ dateOfBirth + ", gender=" + gender + ", address=" + address + ", phone=" + phone + "]";
	}

}
