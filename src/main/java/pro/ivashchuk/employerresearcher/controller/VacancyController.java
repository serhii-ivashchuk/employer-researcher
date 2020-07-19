package pro.ivashchuk.employerresearcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.ivashchuk.employerresearcher.domain.Vacancy;
import pro.ivashchuk.employerresearcher.repository.JpaVacancyRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/vacancies")
public class VacancyController {

    @Autowired
    private JpaVacancyRepository jpaVacancyRepository;

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

    @GetMapping("/addNewVacancy")
    public String getAddNewVacancy(Model model) {
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
}
