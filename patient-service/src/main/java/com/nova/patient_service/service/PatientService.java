package com.nova.patient_service.service;

import com.nova.patient_service.dto.PatientRequestDTO;
import com.nova.patient_service.dto.PatientResponseDTO;
import com.nova.patient_service.exception.EmailAlreadyExistsException;
import com.nova.patient_service.exception.PatientNotFoundException;
import com.nova.patient_service.mapper.PatientMapper;
import com.nova.patient_service.models.Patient;
import com.nova.patient_service.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service

public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        List<PatientResponseDTO> patientDTOs = patients.stream().map(PatientMapper::mapToDTO).toList();

        return patientDTOs;
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {

        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Patient already exists" + patientRequestDTO.getEmail());
        }
        Patient patient = PatientMapper.mapToPatient(patientRequestDTO);
        return PatientMapper.mapToDTO(patientRepository.save(patient));
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
//        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient with ID not found: " + id));
//        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
//            throw new EmailAlreadyExistsException("Patient already exists" + patientRequestDTO.getEmail());
//        }
//        patient.setName(patientRequestDTO.getName());
//        patient.setBirthDate(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
//        patient.setAddress(patientRequestDTO.getAddress());
//        patient.setEmail(patientRequestDTO.getEmail());
//        Patient updatedPatient = patientRepository.save(patient);
//        return PatientMapper.mapToDTO(updatedPatient);

        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new PatientNotFoundException("Patient not found with ID: " + id));

        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "A patient with this email " + "already exists"
                            + patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setBirthDate(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.mapToDTO(updatedPatient);
    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}
