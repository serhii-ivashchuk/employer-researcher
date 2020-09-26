package pro.ivashchuk.employerresearcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.ivashchuk.employerresearcher.domain.CoverLetter;
import pro.ivashchuk.employerresearcher.domain.Vacancy;
import pro.ivashchuk.employerresearcher.repository.JpaCoverLetterRepository;
import pro.ivashchuk.employerresearcher.repository.JpaVacancyRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/coverLetters")
public class CoverLetterController {

    @Autowired
    private JpaCoverLetterRepository jpaCoverLetterRepository;

    @Autowired
    private JpaVacancyRepository jpaVacancyRepository;

    @GetMapping
    public String getAllCoverLetters(Model model) {
        List<CoverLetter> coverLetters = new ArrayList<CoverLetter>(jpaCoverLetterRepository.findAll());
        Collections.sort(coverLetters);
        model.addAttribute("coverLetters", coverLetters);
        return "all_cover_letters";
    }

    @GetMapping("/coverLetter/{id}")
    public String getCoverLetterById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("coverLetter", jpaCoverLetterRepository.findById(id).get());
        return "cover_letter";
    }

    @GetMapping("/addNewCoverLetter/whereVacancyId/{id}")
    public String getAddNewCoverLetterWhereVacancyId(@PathVariable("id") Long id, Model model) {
        Vacancy vacancy = jpaVacancyRepository.findById(id).get();
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("coverLetter", new CoverLetter());
        return "add_cover_letter";
    }

    @PostMapping("/addNewCoverLetter")
    public String postNewCoverLetter(CoverLetter coverLetter) {
        jpaCoverLetterRepository.save(coverLetter);
        return "redirect:/coverLetters";
    }

    @GetMapping("/coverLetter/{id}/update")
    public String getUpdateCoverLetter(@PathVariable("id") Long id, Model model) {
        model.addAttribute("coverLetter", jpaCoverLetterRepository.findById(id).get());
        return "update_cover_letter";
    }

    @PutMapping("/coverLetter/{id}/update")
    public String putUpdatedCoverLetter(@PathVariable("id") Long id, CoverLetter updatedCoverLetter) {
        jpaCoverLetterRepository.save(updatedCoverLetter);
        return "redirect:/coverLetters";
    }

    @DeleteMapping("/coverLetter/{id}/delete")
    public String deleteCoverLetterById(@PathVariable("id") Long id) {
        jpaCoverLetterRepository.deleteById(id);
        return "redirect:/coverLetters";
    }
}
