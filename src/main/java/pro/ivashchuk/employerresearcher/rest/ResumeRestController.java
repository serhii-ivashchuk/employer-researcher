package pro.ivashchuk.employerresearcher.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.ivashchuk.employerresearcher.domain.Resume;
import pro.ivashchuk.employerresearcher.domain.Vacancy;
import pro.ivashchuk.employerresearcher.repository.JpaResumeRepository;
import pro.ivashchuk.employerresearcher.repository.JpaVacancyRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/resumes")
public class ResumeRestController {

    @Autowired
    private JpaResumeRepository jpaResumeRepository;

    @Autowired
    private JpaVacancyRepository jpaVacancyRepository;

    @GetMapping(produces = "application/json")
    public List<Resume> getAllResumes() {
        return jpaResumeRepository.findAll();
    }

    @GetMapping(path = "/resume/{id}", produces = "application/json")
    public Resume getResumeById(@PathVariable("id") Long id) {
        return jpaResumeRepository.findById(id).get();
    }

    @PostMapping("/resume/addNewResume/whereVacancyId/{id}")
    public HttpStatus postNewResume(@PathVariable("id") Long id, @RequestBody Resume resume) {
        Vacancy vacancy = jpaVacancyRepository.findById(id).get();
        jpaResumeRepository.save(resume);
        vacancy.getResumes().add(resume);
        jpaVacancyRepository.save(vacancy);
        resume.setVacancy(vacancy);
        jpaResumeRepository.save(resume);
        return HttpStatus.CREATED;
    }

    @PatchMapping("/resume/{id}/update")
    public ResponseEntity<Resume> putResume(@PathVariable("id") Long id,
                                               Resume updatedResume) {
        Optional<Resume> resumeData = jpaResumeRepository.findById(id);
        if (resumeData.isPresent()) {
            Resume resumeToUpdate = resumeData.get();
            return new ResponseEntity<>(jpaResumeRepository.save(resumeToUpdate), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
