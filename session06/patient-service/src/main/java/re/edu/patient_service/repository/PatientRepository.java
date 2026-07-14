package re.edu.patient_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import re.edu.patient_service.model.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
