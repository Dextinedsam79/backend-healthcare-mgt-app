package com.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.healthcare.entity.Doctor;
import com.healthcare.service.IDoctorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/doctors")
@Tag(name = "Doctors", description = "Operations related to doctors")
public class DoctorController {

    private final IDoctorService doctorService;

    @Autowired
    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @Operation(summary = "Register a new doctor")
    @PostMapping
    public ResponseEntity<Doctor> registerDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.registerDoctor(doctor));
    }
    @Operation(summary = "Get doctor by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }
    @Operation(summary = "Get all doctors")
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }
    @Operation(summary = "Update doctor availability")
    @PutMapping("/{id}/availability")
    public ResponseEntity<Doctor> updateAvailability(@PathVariable Long id, @RequestParam Boolean isAvailable) {
        return ResponseEntity.ok(doctorService.updateAvailability(id, isAvailable));
    }
    @Operation(summary = "Get all available doctors")
    @GetMapping("/available")
    public ResponseEntity<List<Doctor>> getAvailableDoctors() {
        return ResponseEntity.ok(doctorService.getAvailableDoctors());
    }
    @Operation(summary = "Delete doctor by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
