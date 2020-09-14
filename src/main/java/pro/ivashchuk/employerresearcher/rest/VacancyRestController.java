package pro.ivashchuk.employerresearcher.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.ivashchuk.employerresearcher.domain.Employer;
import pro.ivashchuk.employerresearcher.domain.Vacancy;
import pro.ivashchuk.employerresearcher.repository.JpaEmployerRepository;
import pro.ivashchuk.employerresearcher.repository.JpaVacancyRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/vacancies")
public class VacancyRestController {

    @Autowired
    private JpaVacancyRepository jpaVacancyRepository;

    @Autowired
    private JpaEmployerRepository jpaEmployerRepository;

    @GetMapping(produces = "application/json")
    public List<Vacancy> getAllVacancies() {
        return jpaVacancyRepository.findAll();
    }

    @GetMapping(path = "/vacancy/{id}", produces = "application/json")
    public Vacancy getVacancyById(@PathVariable("id") Long id) {
        return jpaVacancyRepository.findById(id).get();
    }

    @PostMapping("/vacancy/addNewVacancy/whereEmployerId/{id}")
    public HttpStatus postNewVacancyWhereEmployerId(@PathVariable("id") Long id, @RequestBody Vacancy vacancy) {
        Employer employer = jpaEmployerRepository.findById(id).get();
        jpaVacancyRepository.save(vacancy);
        employer.getVacancies().add(vacancy);
        jpaEmployerRepository.save(employer);
        vacancy.setEmployer(employer);
        jpaVacancyRepository.save(vacancy);
        return HttpStatus.CREATED;
    }

    @PatchMapping("/vacancy/{id}/update")
    public ResponseEntity<Vacancy> updateVacancy(@PathVariable("id") Long id,
                                                Vacancy updatedVacancy) {
        Optional<Vacancy> vacancyData = jpaVacancyRepository.findById(id);
        if (vacancyData.isPresent()) {
            Vacancy vacancyToUpdate = vacancyData.get();
            return new ResponseEntity<>(jpaVacancyRepository.save(vacancyToUpdate), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/vacancy/{id}/delete")
    public String deleteVacancy(@PathVariable("id") Long id) {
        jpaVacancyRepository.delete(jpaVacancyRepository.findById(id).get());
        return "redirect:localhost:4200/vacancyList";
    }
}
