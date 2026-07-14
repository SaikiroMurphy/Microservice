package re.edu.patient_service.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import re.edu.patient_service.model.dto.request.CreatePatientRequest;
import re.edu.patient_service.model.dto.response.PatientResponse;
import re.edu.patient_service.model.entity.Patient;
import re.edu.patient_service.repository.PatientRepository;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientResponse createPatient(CreatePatientRequest request) {
        Patient patient = new Patient();
        patient.setFullName(request.getFullName());
        patient.setAddress(request.getAddress());
        patient.setMedicalHistory(request.getMedicalHistory());

        Patient savedPatient = patientRepository.save(patient);

        return new PatientResponse(
                savedPatient.getId(),
                savedPatient.getFullName(),
                savedPatient.getDateOfBirth(),
                savedPatient.getGender(),
                savedPatient.getPhoneNumber(),
                savedPatient.getAddress(),
                savedPatient.getMedicalHistory()
        );
    }
}
