package by.itstep.hospital.repository;

import by.itstep.hospital.model.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PatientRepository extends CrudRepository<Patient, Long>,
        PagingAndSortingRepository<Patient, Long>
{

}
