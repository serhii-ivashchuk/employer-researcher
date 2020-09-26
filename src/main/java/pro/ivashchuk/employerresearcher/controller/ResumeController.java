package pro.ivashchuk.employerresearcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.ivashchuk.employerresearcher.domain.Resume;
import pro.ivashchuk.employerresearcher.repository.JpaResumeRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/resumes")
public class ResumeController {

    @Autowired
    private JpaResumeRepository jpaResumeRepository;

    @Autowired
    private JpaVacancyRepository jpaVacancyRepository;

    @GetMapping
    public String getAllResumes(Model model) {
        List<Resume> resumes = new ArrayList<Resume>(jpaResumeRepository.findAll());
        Collections.sort(resumes);
        model.addAttribute("resumes", resumes);
        return "all_resumes";
    }

    @GetMapping("/resume/{id}")
    public String getResumeById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("resume", jpaResumeRepository.findById(id).get());
        return "resume";
    }

    @GetMapping("/addNewResume/whereVacancyId/{id}")
    public String getAddNewResumeWhereVacancyId(@PathVariable("id") Long id, Model model) {
        Vacancy vacancy = jpaVacancyRepository.findById(id).get();
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("resume", new Resume());
        return "add_resume";
    }

    @PostMapping("/addNewResume")
    public String postNewResume(Resume resume) {
        jpaResumeRepository.save(resume);
        return "redirect:/resumes";
    }

    @GetMapping("/resume/{id}/update")
    public String getUpdateResume(@PathVariable("id") Long id, Model model) {
        model.addAttribute("resume", jpaResumeRepository.findById(id).get());
        return "update_resume";
    }

    @PutMapping("/resume/{id}/update")
    public String putUpdatedResume(@PathVariable("id") Long id, Resume updatedResume) {
        jpaResumeRepository.save(updatedResume);
        return "redirect:/resumes";
    }

    @DeleteMapping("/resume/{id}/delete")
    public String deleteResumeById(@PathVariable("id") Long id) {
        jpaResumeRepository.deleteById(id);
        return "redirect:/resumes";
    }
}
