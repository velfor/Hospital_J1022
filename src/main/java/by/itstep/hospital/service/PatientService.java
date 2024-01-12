package by.itstep.hospital.service;

import by.itstep.hospital.model.Patient;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PatientService {
    Patient save(Patient patient);

    Optional<Patient> findById(Long id);

    boolean existsById(Long id);

    Iterable<Patient> findAll();

    Page<Patient> findPaginated(int pageNumber, int pageSize);

    Page<Patient> findPaginatedAndSorted(int pageNumber, int pageSize, String sortField, String sortDirection);

    void deleteById(Long id);

    void delete(Patient patient);
}
