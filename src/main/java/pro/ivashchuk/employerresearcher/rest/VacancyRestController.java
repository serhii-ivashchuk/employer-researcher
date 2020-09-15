package pro.ivashchuk.employerresearcher.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.ivashchuk.employerresearcher.domain.Vacancy;
import pro.ivashchuk.employerresearcher.repository.JpaVacancyRepository;

import java.util.List;
import java.util.Optional;

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

    //patch or put mapping
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
}
