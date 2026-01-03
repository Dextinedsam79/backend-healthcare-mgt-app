package com.healthcare.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@ManyToOne
private Patient patient;


@ManyToOne
private Doctor doctor;


private LocalDateTime appointmentDate;


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public Patient getPatient() {
	return patient;
}


public void setPatient(Patient patient) {
	this.patient = patient;
}


public Doctor getDoctor() {
	return doctor;
}


public void setDoctor(Doctor doctor) {
	this.doctor = doctor;
}


public LocalDateTime getAppointmentDate() {
	return appointmentDate;
}


public void setAppointmentDate(LocalDateTime appointmentDate) {
	this.appointmentDate = appointmentDate;
}

}
