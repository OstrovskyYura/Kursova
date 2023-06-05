package com.example.final20.controllers;

import com.example.final20.dao.PatientsRepository;
import com.example.final20.entities.Patients;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class PatientsController {

    private PatientsRepository patientsRepository;

    @GetMapping("/patients")
    public String showPatients(Model model) {
        List<Patients> patients = patientsRepository.findByNameNotNullOrderByName();
        model.addAttribute("patients", patients);
        return "patients";
    }

    @PostMapping("/add_patients")
    public String addPatients(@RequestParam String name, @RequestParam String address, @RequestParam String phone,
                             @RequestParam int medical_card_number, @RequestParam String diagnosis) {
        Patients patients = new Patients();
        patients.setName(name);
        patients.setAddress(address);
        patients.setPhone(phone);
        patients.setMedicalCardNumber(medical_card_number);
        patients.setDiagnosis(diagnosis);
        patientsRepository.save(patients);
        return "redirect:/patients";
    }

    @GetMapping("/delete_patients")
    public String deletePatients(@RequestParam int id) {
        patientsRepository.deleteById(id);
        return "redirect:/patients";
    }

    @GetMapping("/edit_patients")
    public String editPatients(@RequestParam int id, Model model) {
        Optional<Patients> optionalPatients = patientsRepository.findById(id);
        if (optionalPatients.isEmpty()) {
            return "redirect:/patients";
        }
        model.addAttribute("patients", optionalPatients.get());
        return "edit_patients";
    }

    @PostMapping("/update_patients")
    public String updateReception(@RequestParam int id, @RequestParam String name, @RequestParam String address,
                                  @RequestParam("tphone") String phone, @RequestParam int medical_card_number,
                                  @RequestParam String diagnosis) {
        Optional<Patients> optionalPatients = patientsRepository.findById(id);
        optionalPatients.ifPresent(t -> {
            t.setName(name);
            t.setAddress(address);
            t.setPhone(phone);
            t.setMedicalCardNumber(medical_card_number);
            t.setDiagnosis(diagnosis);
            patientsRepository.save(t);
        });
        return "redirect:/patients";
    }
}
