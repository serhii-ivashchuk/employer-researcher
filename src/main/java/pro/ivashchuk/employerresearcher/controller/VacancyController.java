package pro.ivashchuk.employerresearcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.ivashchuk.employerresearcher.domain.Employer;
import pro.ivashchuk.employerresearcher.domain.Vacancy;
import pro.ivashchuk.employerresearcher.repository.JpaEmployerRepository;
import pro.ivashchuk.employerresearcher.repository.JpaVacancyRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/vacancies")
public class VacancyController {

    @Autowired
    private JpaVacancyRepository jpaVacancyRepository;

    @Autowired
    private JpaEmployerRepository jpaEmployerRepository;

    @GetMapping
    public String getAllVacancies(Model model) {
        List<Vacancy> vacancies = new ArrayList<Vacancy>(jpaVacancyRepository.findAll());
        Collections.sort(vacancies);
        model.addAttribute("vacancies", vacancies);
        return "all_vacancies";
    }

    @GetMapping("/vacancy/{id}")
    public String getVacancyById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("vacancy", jpaVacancyRepository.findById(id).get());
        return "vacancy";
    }

    @GetMapping("/addNewVacancy/whereEmployerId/{id}")
    public String getAddNewVacancyWhereEmployerId(@PathVariable("id") Long id, Model model) {
        Employer employer = jpaEmployerRepository.findById(id).get();
        model.addAttribute("employer", employer);
        model.addAttribute("vacancy", new Vacancy());
        return "add_vacancy";
    }

    @PostMapping("/addNewVacancy")
    public String postNewVacancy(Vacancy vacancy) {
        jpaVacancyRepository.save(vacancy);
        return "redirect:/vacancies";
    }

    @GetMapping("/vacancy/{id}/update")
    public String getUpdateVacancy(@PathVariable("id") Long id, Model model) {
        model.addAttribute("vacancy", jpaVacancyRepository.findById(id).get());
        return "update_vacancy";
    }

    @PutMapping("/vacancy/{id}/update")
    public String putUpdatedVacancy(@PathVariable("id") Long id, Vacancy updatedVacancy) {
        jpaVacancyRepository.save(updatedVacancy);
        return "redirect:/vacancies";
    }

    @DeleteMapping("/vacancy/{id}/delete")
    public String deleteVacancyById(@PathVariable("id") Long id) {
        jpaVacancyRepository.deleteById(id);
        return "redirect:/vacancies";
    }
}
