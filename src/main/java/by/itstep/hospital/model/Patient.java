package by.itstep.hospital.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldNameConstants
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="full_name", length = 50)
    private String fullName;

    @Column(name="gender", length = 8)
    private String gender;

    @Column(name="date_of_birth", columnDefinition = "DATE")
    private LocalDate dateOfBirth;

    @Column(name="diagnosis", length = 100)
    private String diagnosis;

    @Column(name="date_of_admission", columnDefinition = "DATE")
    private LocalDate dateOfAdmission;

    @Column(name="date_of_discharge", columnDefinition = "DATE")
    private LocalDate dateOfDischarge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
}
