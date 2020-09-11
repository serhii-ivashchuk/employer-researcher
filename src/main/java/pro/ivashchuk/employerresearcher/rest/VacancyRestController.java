package pro.ivashchuk.employerresearcher.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.ivashchuk.employerresearcher.domain.Vacancy;
import pro.ivashchuk.employerresearcher.repository.JpaVacancyRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/vacancies")
public class VacancyRestController {

    @Autowired
    private JpaVacancyRepository jpaVacancyRepository;

    @GetMapping(produces = "application/json")
    public List<Vacancy> getAllVacancies() {
        return jpaVacancyRepository.findAll();
    }
}
