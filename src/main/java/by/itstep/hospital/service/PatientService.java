package by.itstep.hospital.service;

import by.itstep.hospital.model.Patient;
import java.util.Optional;

public interface PatientService {
    Patient save(Patient patient);
    Optional<Patient> findById(Long id);
    boolean existsById(Long id);
    Iterable<Patient> findAll();
}
