package pro.ivashchuk.employerresearcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.ivashchuk.employerresearcher.repository.JpaCoverLetterRepository;

@Controller
@RequestMapping("/coverLetters")
public class CoverLetterController {

    @Autowired
    private JpaCoverLetterRepository jpaCoverLetterRepository;
}