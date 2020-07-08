package pro.ivashchuk.employerresearcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.ivashchuk.employerresearcher.domain.Employer;
import pro.ivashchuk.employerresearcher.repository.JpaEmployerRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/employers")
public class EmployerController {

    @Autowired
    private JpaEmployerRepository jpaEmployerRepository;

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

    @GetMapping("/addNewEmployer")
    public String getAddNewEmployer(Model model) {
        model.addAttribute("employer", new Employer());
        return "add_employer";
    }

    @PostMapping("/addNewEmployer")
    public String postNewEmployer(Employer employer) {
        jpaEmployerRepository.save(employer);
        return "redirect:/employers";
    }

    @GetMapping("/employer/{id}/update")
    public String getUpdateEmployer(@PathVariable("id") Long id, Model model) {
        model.addAttribute("employer", jpaEmployerRepository.findById(id).get());
        return "update_employer";
    }
}
