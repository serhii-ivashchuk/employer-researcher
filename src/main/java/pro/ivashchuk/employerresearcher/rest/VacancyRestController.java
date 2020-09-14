package pro.ivashchuk.employerresearcher.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(path = "/vacancy/{id}", produces = "application/json")
    public Vacancy getVacancyById(@PathVariable("id") Long id) {
        return jpaVacancyRepository.findById(id).get();
    }

    @PostMapping("/vacancy/addNewVacancy")
    public HttpStatus addNewVacancy(@RequestBody Vacancy vacancy) {
        jpaVacancyRepository.save(vacancy);
        return HttpStatus.CREATED;
    }
}
