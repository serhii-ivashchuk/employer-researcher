package pro.ivashchuk.employerresearcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.ivashchuk.employerresearcher.domain.Candidate;
import pro.ivashchuk.employerresearcher.repository.JpaCandidateRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private JpaCandidateRepository jpaCandidateRepository;

    @GetMapping
    public String getAllCandidates(Model model) {
        List<Candidate> candidates = new ArrayList<Candidate>(jpaCandidateRepository.findAll());
        Collections.sort(candidates);
        model.addAttribute("candidates", candidates);
        return "all_candidates";
    }

    @GetMapping("/candidate/{id}")
    public String getCandidateById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("candidate", jpaCandidateRepository.findById(id).get());
        return "candidate";
    }

    @GetMapping("/addNewCandidate")
    public String getAddNewCandidate(Model model) {
        model.addAttribute("candidate", new Candidate());
        return "add_candidate";
    }

    @PostMapping("/addNewCandidate")
    public String postNewCandidate(Candidate candidate) {
        jpaCandidateRepository.save(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/candidate/{id}/update")
    public String getUpdateCandidate(@PathVariable("id") Long id, Model model) {
        model.addAttribute("candidate", jpaCandidateRepository.findById(id).get());
        return "update_candidate";
    }

    @PutMapping("/candidate/{id}/update")
    public String putUpdatedCandidate(@PathVariable("id") Long id, Candidate updatedCandidate) {
        jpaCandidateRepository.save(updatedCandidate);
        return "redirect:/candidates";
    }
}
