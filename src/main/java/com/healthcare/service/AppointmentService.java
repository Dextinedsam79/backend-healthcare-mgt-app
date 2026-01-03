package com.healthcare.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.entity.Appointment;
import com.healthcare.entity.Doctor;
import com.healthcare.entity.Patient;
import com.healthcare.repository.AppointmentRepository;
import com.healthcare.repository.DoctorRepository;
import com.healthcare.repository.PatientRepository;

@Service
public class AppointmentService implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              PatientRepository patientRepository,
                              DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Appointment bookAppointment(long patientId, Long doctorId, LocalDate date) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));

        if (!doctor.getIsAvailable()) {
            throw new RuntimeException("Doctor is not available for appointment.");
        }

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(date.atStartOfDay()); // Convert LocalDate to LocalDateTime

        // Optionally mark doctor unavailable if only one appointment per day allowed
        // doctor.setIsAvailable(false);
        // doctorRepository.save(doctor);

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + appointmentId));

        // If you want, you can also set doctor availability back to true
        // Doctor doctor = appointment.getDoctor();
        // doctor.setIsAvailable(true);
        // doctorRepository.save(doctor);

        appointmentRepository.delete(appointment);
        return appointment;
    }

    @Override
    public Appointment completeAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + appointmentId));

        // Here you can mark appointment as completed using a status field (optional)
        // For now, let's just delete after completion
        // Or just return the appointment as "completed"
        return appointment;
    }

    @Override
    public List<Appointment> getAppointsByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    @Override
    public List<Appointment> getAppointmentByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }
}
