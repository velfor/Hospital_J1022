package by.itstep.hospital.controller;

import by.itstep.hospital.model.Department;
import by.itstep.hospital.model.Diagnosis;
import by.itstep.hospital.model.Patient;
import by.itstep.hospital.repository.DepartmentRepository;
import by.itstep.hospital.service.PatientServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@AllArgsConstructor
public class PatientController {

    private final PatientServiceImpl patientService;
    private final DepartmentRepository departmentRepository;

    @ModelAttribute("allDiagnosis")
    public List<String> populateDiagnosis() {
        return  Arrays.stream(Diagnosis.values())
                .map(Diagnosis::getRussianName)
                .collect(Collectors.toList());
    }

    @ModelAttribute("departmentsList")
    public List<Department> populateDepartments() {
        Iterable<Department> departments = departmentRepository.findAll();
        return StreamSupport.stream(departments.spliterator(),false)
                .collect(Collectors.toList());
    }

    @GetMapping("/")
    public String showMenu(){
        return "index";
    }

    @GetMapping("/patients")
    public String showPatientsList(Model model){
        Iterable<Patient> patientsList = patientService.findAll();
        model.addAttribute("patientsList", patientsList);
        return "patients";
    }

    @GetMapping("/add_patient")
    public String showInputForm(@ModelAttribute("patient") Patient patient){
        return "add_patient";
    }

    @PostMapping("add_patient")
    public String addPAtient(Patient patient)
    {
        patientService.save(patient);
        return "add_success";
    }
}
