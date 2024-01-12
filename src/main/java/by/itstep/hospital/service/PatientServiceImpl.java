package by.itstep.hospital.service;

import by.itstep.hospital.model.Patient;
import by.itstep.hospital.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Patient save(Patient patient) {
        return this.patientRepository.save(patient);
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return this.patientRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return this.patientRepository.existsById(id);
    }

    @Override
    public Iterable<Patient> findAll() {
        return this.patientRepository.findAll();
    }

    @Override
    public Page<Patient> findPaginated(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return this.patientRepository.findAll(pageable);
    }

    @Override
    public Page<Patient> findPaginatedAndSorted(int pageNumber, int pageSize, String sortField, String sortDirection) {
        /*Sort sort;
        if (sortDirection.equalsIgnoreCase("asc"))
            sort = Sort.by(sortField).ascending();
        else
            sort = Sort.by(sortField).descending();*/
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return this.patientRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id){
        this.patientRepository.deleteById(id);
    }

    @Override
    public void delete(Patient patient) {
        this.patientRepository.delete(patient);
    }
}
