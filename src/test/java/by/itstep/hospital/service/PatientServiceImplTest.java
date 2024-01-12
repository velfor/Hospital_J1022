package by.itstep.hospital.service;

import by.itstep.hospital.model.Patient;
import by.itstep.hospital.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PatientServiceImplTest {

    @Mock
    PatientRepository patientRepository;

    @Test
    void findAllTest() {
        List<Patient> patients = new ArrayList<>();
        patients.add(Patient.builder()
                .fullName("Иванов Иван Иванович")
                .diagnosis("Вывих")
                .build()
        );
        patients.add(Patient.builder()
                .fullName("Петров Петр Петрович")
                .diagnosis("Перелом")
                .build()
        );

        when(patientRepository.findAll()).thenReturn(patients);

        List<Patient> expected = StreamSupport.stream(patientRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        assertEquals(expected.size(), 2);
        assertEquals(expected.get(0).getFullName(),"Иванов Иван Иванович");
    }


}