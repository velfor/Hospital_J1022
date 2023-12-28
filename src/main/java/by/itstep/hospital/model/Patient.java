package by.itstep.hospital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="full_name", length = 50)
    private String fullName;
    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name="diagnosis", length = 100)
    private String diagnosis;
    @Column(name="date_of_admission")
    private LocalDate dateOfAdmission;
    @Column(name="date_of_discharge")
    private LocalDate dateOfDischarge;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
}
