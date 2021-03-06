package pro.ivashchuk.employerresearcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.ivashchuk.employerresearcher.domain.Candidate;
import pro.ivashchuk.employerresearcher.domain.Employer;
import pro.ivashchuk.employerresearcher.repository.JpaCandidateRepository;
import pro.ivashchuk.employerresearcher.repository.JpaEmployerRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/employers")
public class EmployerController {

    @Autowired
    private JpaEmployerRepository jpaEmployerRepository;

    @Autowired
    private JpaCandidateRepository jpaCandidateRepository;

    @GetMapping
    public String getAllEmployers(Model model) {
        List<Employer> employers = new ArrayList<Employer>(jpaEmployerRepository.findAll());
        Collections.sort(employers);
        model.addAttribute("employers", employers);
        return "all_employers";
    }

    @GetMapping("/employer/{id}")
    public String getEmployerById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("employer", jpaEmployerRepository.findById(id).get());
        return "employer";
    }

    @GetMapping("/addNewEmployer/whereCandidateId/{id}")
    public String getAddNewEmployerWhereCandidateId(@PathVariable("id") Long id, Model model) {
        Candidate candidate = jpaCandidateRepository.findById(id).get();
        model.addAttribute("candidate", candidate);
        model.addAttribute("employer", new Employer());
        return "add_employer";
    }

    @PostMapping("/addNewEmployer/whereCandidateId/{id}")
    public String postNewEmployerWhereCandidateId(@PathVariable("id") Long id, Employer employer) {
        Candidate candidate = jpaCandidateRepository.findById(id).get();
        jpaEmployerRepository.save(employer);
        candidate.getEmployers().add(employer);
        jpaCandidateRepository.save(candidate);
        employer.setCandidate(candidate);
        jpaEmployerRepository.save(employer);
        String emplId = employer.getId().toString();
        return "redirect:/employers/employer/" + emplId;
    }

    @GetMapping("/employer/{id}/update")
    public String getUpdateEmployer(@PathVariable("id") Long id, Model model) {
        model.addAttribute("employer", jpaEmployerRepository.findById(id).get());
        return "update_employer";
    }

    @PutMapping("/employer/{id}/update")
    public String putUpdatedEmployer(@PathVariable("id") Long id, Employer updatedEmployer) {
        jpaEmployerRepository.save(updatedEmployer);
        return "redirect:/employers";
    }

    @DeleteMapping("/employer/{id}/delete")
    public String deleteEmployerById(@PathVariable("id") Long id) {
        jpaEmployerRepository.deleteById(id);
        return "redirect:/employers";
    }
}
