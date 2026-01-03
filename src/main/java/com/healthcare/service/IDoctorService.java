package com.healthcare.service;

import java.util.List;

import com.healthcare.entity.Doctor;

public interface IDoctorService {

    Doctor registerDoctor(Doctor doctor);

    Doctor getDoctorById(Long id);
    List<Doctor> getAllDoctors();
    Doctor updateAvailability(Long doctorId, Boolean isAvailable);
List<Doctor>getAvailableDoctors();
    void deleteDoctor(Long id);
    

    
}

