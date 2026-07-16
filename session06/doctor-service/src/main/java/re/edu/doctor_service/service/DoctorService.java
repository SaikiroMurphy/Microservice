package re.edu.doctor_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import re.edu.doctor_service.model.dto.request.CreateDoctorRequest;
import re.edu.doctor_service.model.dto.response.ApiResponseError;
import re.edu.doctor_service.model.dto.response.DoctorResponse;
import re.edu.doctor_service.model.entity.Doctor;
import re.edu.doctor_service.repository.DoctorRepository;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    // public DoctorResponse createDoctor(CreateDoctorRequest request) {
    //     Doctor doctor = new Doctor();
    //     doctor.setFullName(request.getFullName());
    //     doctor.setAddress(request.getAddress());
    //     doctor.setMedicalHistory(request.getMedicalHistory());

    //     Doctor savedDoctor = doctorRepository.save(doctor);

    //     return new DoctorResponse(
    //             savedDoctor.getId(),
    //             savedDoctor.getFullName(),
    //             savedDoctor.getDateOfBirth(),
    //             savedDoctor.getGender(),
    //             savedDoctor.getPhoneNumber(),
    //             savedDoctor.getAddress(),
    //             savedDoctor.getMedicalHistory()
    //     );
    // }

    public List<DoctorResponse> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream()
                .map(doctor -> new DoctorResponse(
                        doctor.getId(),
                        doctor.getFullName(),
                        doctor.getSpecialization(),
                        doctor.getExperienceYears(),
                        doctor.getEmail(),
                        doctor.getStatus()
                ))
                .toList();
    }

    public DoctorResponse getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());

        return new DoctorResponse(
                doctor.getId(),
                doctor.getFullName(),
                doctor.getSpecialization(),
                doctor.getExperienceYears(),
                doctor.getEmail(),
                doctor.getStatus()
        );
    }
}
