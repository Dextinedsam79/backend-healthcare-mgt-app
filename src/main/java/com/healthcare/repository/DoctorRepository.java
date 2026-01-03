package com.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	List<Doctor> findByIsAvailableTrue();
}
