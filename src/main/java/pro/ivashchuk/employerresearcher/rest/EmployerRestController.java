package pro.ivashchuk.employerresearcher.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.ivashchuk.employerresearcher.domain.Employer;
import pro.ivashchuk.employerresearcher.repository.JpaEmployerRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/employers")
public class EmployerRestController {

    @Autowired
    private JpaEmployerRepository jpaEmployerRepository;

    @GetMapping(produces = "application/json")
    public List<Employer> getAllEmployers() {
        return jpaEmployerRepository.findAll();
    }

    @GetMapping(path = "/employer/{id}", produces = "application/json")
    public Employer getEmployerById(@PathVariable("id")  Long id) {
        return jpaEmployerRepository.findById(id).get();
    }

    @PostMapping("/employer/addNewEmployer")
    public HttpStatus addNewEmployer(@RequestBody Employer employer) {
        jpaEmployerRepository.save(employer);
        return HttpStatus.CREATED;
    }

    @PutMapping("/employer/{id}/update")
    public ResponseEntity<Employer> updateEmployer(@PathVariable("id") Long id,
                                                   Employer updatedEmployer) {
        Optional<Employer> employerData = jpaEmployerRepository.findById(id);
        if (employerData.isPresent()) {
            return new ResponseEntity<>(jpaEmployerRepository.save(updatedEmployer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("employer/{id}/delete")
    public String deleteEmployer(@PathVariable("id") Long id) {
        jpaEmployerRepository.deleteById(id);
        return "redirect:localhost:4200/employerList";
    }
}