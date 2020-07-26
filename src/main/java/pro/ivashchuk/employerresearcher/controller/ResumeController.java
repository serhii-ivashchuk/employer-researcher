package pro.ivashchuk.employerresearcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/addNewResume")
    public String getAddNewResume(Model model) {
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
}
