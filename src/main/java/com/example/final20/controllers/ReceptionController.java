package com.example.final20.controllers;

import com.example.final20.dao.ReceptionRepository;
import com.example.final20.entities.Reception;
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
public class ReceptionController {

    private ReceptionRepository receptionRepository;

    @GetMapping("/reception")
    public String showReception(Model model) {
        List<Reception> receptions = receptionRepository.findAll();
        model.addAttribute("reception", receptions);
        return "reception";
    }

    @GetMapping("/reception_doctor")
    public String showDoctorByReception(@RequestParam long id, Model model) {
        Optional<Reception> optionalReception = receptionRepository.findById(id);
        if (optionalReception.isPresent()) {
            model.addAttribute("reception", optionalReception.get());
            return "doctor_by_reception";
        } else {
            return "redirect:/reception";
        }
    }

    @PostMapping("/add_reception")
    public String addReception(@RequestParam int turn, @RequestParam String time, @RequestParam String name) {
        Reception reception = new Reception();
        reception.setTurn(turn);
        reception.setTime(time);
        reception.setName(name);
        receptionRepository.save(reception);
        return "redirect:/reception";
    }

    @GetMapping("/delete_reception")
    public String deleteReception(@RequestParam long id) {
        receptionRepository.deleteById(id);
        return "redirect:/reception";
    }

    @GetMapping("/edit_reception")
    public String editReception(@RequestParam long id, Model model) {
        Optional<Reception> optionalReception = receptionRepository.findById(id);
        if (optionalReception.isEmpty()) {
            return "redirect:/reception";
        }
        model.addAttribute("reception", optionalReception.get());
        return "edit_reception";
    }

    @PostMapping("/update_reception")
    public String updateReception(@RequestParam long id, @RequestParam int turn, @RequestParam String time,
                                  @RequestParam("tname") String name) {
        Optional<Reception> optionalReception = receptionRepository.findById(id);
        optionalReception.ifPresent(t -> {
            t.setTurn(turn);
            t.setTime(time);
            t.setName(name);
            receptionRepository.save(t);
        });
        return "redirect:/reception";
    }
}