package by.itstep.hospital.controller;

import by.itstep.hospital.model.Department;
import by.itstep.hospital.model.Diagnosis;
import by.itstep.hospital.model.Patient;
import by.itstep.hospital.repository.DepartmentRepository;
import by.itstep.hospital.service.PatientServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@AllArgsConstructor
public class PatientController {
    private final PatientServiceImpl patientService;
    private final DepartmentRepository departmentRepository;
    private final int PAGE_SIZE = 5;

    @ModelAttribute("allDiagnosis")
    public List<String> populateDiagnosis() {
        return Arrays.stream(Diagnosis.values())
                .map(Diagnosis::getRussianName)
                .collect(Collectors.toList());
    }

    @ModelAttribute("departmentsList")
    public List<Department> populateDepartments() {
        Iterable<Department> departments = departmentRepository.findAll();
        return StreamSupport.stream(departments.spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/")
    public String showMenu() {
        return "index";
    }

    @GetMapping("/patients")
    public String showPatientsList(Model model) {
        return showPatientsListPaginatedAndSorted(1,"fullName", "asc", model);
    }

    @GetMapping("/add_patient")
    public String showInputForm(@ModelAttribute("patient") Patient patient) {
        return "add_patient";
    }

    @PostMapping("add_patient")
    public String addPAtient(Patient patient) {
        patientService.save(patient);
        return "add_success";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable("id") Long id){
        this.patientService.deleteById(id);
        return "redirect:/patients";
    }

    /*@GetMapping("/page/{pageNumber}")
    public String showPatientsListPaginated(@PathVariable(value = "pageNumber") int pageNumber,
            Model model)
    {
        Page<Patient> page = patientService.findPaginated(pageNumber, PAGE_SIZE);
        var patientsList = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("patientsList", patientsList);

        return "patients";
    }*/

    // page/1?sortField=name&sortDir=asc
    @GetMapping("/page/{pageNumber}")
    public String showPatientsListPaginatedAndSorted(@PathVariable(value = "pageNumber") int pageNumber,
                                                     @RequestParam("sortField") String sortField,
                                                     @RequestParam("sortDir") String sortDir,
                                                     Model model) {
        Page<Patient> page = patientService.findPaginatedAndSorted(pageNumber, PAGE_SIZE, sortField, sortDir);
        var patientsList = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("patientsList", patientsList);
        //для сортировки
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "patients";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model){
        Optional<Patient> patient = patientService.findById(id);
        model.addAttribute("patient", patient.get());
        return "update";
    }

    @PostMapping("/save")
    public String savePatient(@ModelAttribute("patient") Patient patient){
        patientService.save(patient);
        return "redirect:/patients";
    }
}
