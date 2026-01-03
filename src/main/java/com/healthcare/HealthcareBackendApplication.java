package com.healthcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Healthcare Management API",
                version = "1.0",
                description = "API for managing Patients, Doctors, and Appointments"
        )
)
public class HealthcareBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthcareBackendApplication.class, args);
    }
}
