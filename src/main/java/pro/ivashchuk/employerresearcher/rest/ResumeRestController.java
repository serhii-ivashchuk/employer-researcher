package pro.ivashchuk.employerresearcher.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.ivashchuk.employerresearcher.domain.Resume;
import pro.ivashchuk.employerresearcher.repository.JpaResumeRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/resumes")
public class ResumeRestController {

    @Autowired
    private JpaResumeRepository jpaResumeRepository;

    @GetMapping(produces = "application/json")
    public List<Resume> getAllResumes() {
        return jpaResumeRepository.findAll();
    }
}
