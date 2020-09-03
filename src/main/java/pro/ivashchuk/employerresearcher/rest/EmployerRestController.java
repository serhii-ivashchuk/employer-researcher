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
}