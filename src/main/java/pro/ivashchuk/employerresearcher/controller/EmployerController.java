package pro.ivashchuk.employerresearcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
