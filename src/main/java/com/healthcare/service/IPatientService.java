package com.healthcare.service;

import java.util.List;

import com.healthcare.entity.Patient;

public interface IPatientService {
Patient createPatient(Patient patient);
Patient getPatientById(Long id);
List<Patient> getAllPatient();
Patient updatePatient(Long id, Patient patient);
void deletePatient(Long id);
} 