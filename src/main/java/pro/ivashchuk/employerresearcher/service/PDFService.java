package pro.ivashchuk.employerresearcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.ivashchuk.employerresearcher.repository.JpaCoverLetterRepository;
import pro.ivashchuk.employerresearcher.repository.JpaCoverLetterTemplateRepository;
import pro.ivashchuk.employerresearcher.repository.JpaResumeRepository;
import pro.ivashchuk.employerresearcher.repository.JpaResumeTemplateRepository;

@Service
public class PDFService {

    @Autowired
    private JpaResumeRepository jpaResumeRepository;

    @Autowired
    private JpaResumeTemplateRepository jpaResumeTemplateRepository;

    @Autowired
    private JpaCoverLetterRepository jpaCoverLetterRepository;

    @Autowired
    private JpaCoverLetterTemplateRepository jpaCoverLetterTemplateRepository;
}
