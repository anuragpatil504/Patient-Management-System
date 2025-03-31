package com.nova.patient_service.mapper;

import com.nova.patient_service.dto.PatientRequestDTO;
import com.nova.patient_service.dto.PatientResponseDTO;
import com.nova.patient_service.models.Patient;

import java.time.LocalDate;
import java.util.UUID;

public class PatientMapper {

    public static PatientResponseDTO mapToDTO(Patient patient) {
        PatientResponseDTO patientDTO = new PatientResponseDTO();
        patientDTO.setId(String.valueOf(patient.getId()));
        patientDTO.setName(patient.getName());
        patientDTO.setAddress(String.valueOf(patient.getAddress()));
        patientDTO.setEmail(String.valueOf(patient.getEmail()));
        patientDTO.setDateOfBirth(String.valueOf(patient.getBirthDate()));
        return patientDTO;
    }
    public static Patient mapToPatient(PatientRequestDTO patientDTO) {
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setAddress(patientDTO.getAddress());
        patient.setEmail(patientDTO.getEmail());
        patient.setBirthDate(LocalDate.parse(patientDTO.getDateOfBirth()));
        patient.setRegistrationDate(LocalDate.parse(patientDTO.getRegistrationDate()));
        return patient;
    }
}
