package com.healthcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.entity.Patient;
import com.healthcare.repository.PatientRepository;

@Service
public class PatientService implements IPatientService {

    private final PatientRepository patientRepository;

  
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient); // Save patient to DB
    }

    @Override
    public Patient getPatientById(Long id) {
        Optional<Patient> patientOpt = patientRepository.findById(id);
        if (patientOpt.isPresent()) {
            return patientOpt.get();
        } else {
            throw new RuntimeException("Patient not found with id: " + id);
        }
    }

    @Override
    public List<Patient> getAllPatient() {
        return patientRepository.findAll(); // Fetch all patients
    }

    @Override
    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient existingPatient = getPatientById(id); // Throws if not found

        // Update fields (example, adjust according to your Patient entity)
        existingPatient.setName(patientDetails.getName());
        existingPatient.setAge(patientDetails.getAge());
        existingPatient.setPhone(patientDetails.getPhone());
        existingPatient.setEmail(patientDetails.getEmail());
        existingPatient.setMedicalHistory(patientDetails.getMedicalHistory());

        return patientRepository.save(existingPatient); // Save updated patient
    }

    @Override
    public void deletePatient(Long id) {
        Patient existingPatient = getPatientById(id); // Throws if not found
        patientRepository.delete(existingPatient); // Delete patient
    }
}
