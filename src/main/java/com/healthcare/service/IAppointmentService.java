package com.healthcare.service;

import java.time.LocalDate;
import java.util.List;

import com.healthcare.entity.Appointment;

public interface IAppointmentService {
Appointment bookAppointment(long patientId, Long doctorId, LocalDate date);
Appointment cancelAppointment(Long appointmentId);
Appointment completeAppointment(Long appointmentId);
List<Appointment>getAppointsByPatient(Long patientId);
List<Appointment>getAppointmentByDoctor(Long doctorId);
}
