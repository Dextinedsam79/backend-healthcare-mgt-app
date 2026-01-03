package com.healthcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.entity.Doctor;
import com.healthcare.repository.DoctorRepository;

@Service
public class DoctorService implements IDoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor registerDoctor(Doctor doctor) {
        // Default available status is true if not set
        if (doctor.getIsAvailable() == null) {
            doctor.setIsAvailable(true);
        }
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctorById(Long id) {
        Optional<Doctor> doctorOpt = doctorRepository.findById(id);
        if (doctorOpt.isPresent()) {
            return doctorOpt.get();
        } else {
            throw new RuntimeException("Doctor not found with id: " + id);
        }
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor updateAvailability(Long doctorId, Boolean isAvailable) {
        Doctor existingDoctor = getDoctorById(doctorId); // throws if not found
        existingDoctor.setIsAvailable(isAvailable);
        return doctorRepository.save(existingDoctor);
    }

    @Override
    public List<Doctor> getAvailableDoctors() {
    	//return doctorRepository.findAll().stream().filter(Doctor::isAvailable).toList();
        return doctorRepository.findByIsAvailableTrue();
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor existingDoctor = getDoctorById(id); // throws if not found
        doctorRepository.delete(existingDoctor);
    }
}
