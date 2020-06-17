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
}
