package by.itstep.hospital.repository;

import by.itstep.hospital.model.Department;
import by.itstep.hospital.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
}
