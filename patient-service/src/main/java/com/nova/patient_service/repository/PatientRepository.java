package com.nova.patient_service.repository;

import com.nova.patient_service.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    boolean existsByEmail(String email);

    List<Patient> getPatientById(UUID id);
    boolean existsByEmailAndIdNot(String email, UUID id);
}
