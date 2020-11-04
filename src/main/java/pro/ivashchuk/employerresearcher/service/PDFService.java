package pro.ivashchuk.employerresearcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pro.ivashchuk.employerresearcher.domain.CoverLetter;
import pro.ivashchuk.employerresearcher.domain.Resume;
import pro.ivashchuk.employerresearcher.domain.pdf.CoverLetterTemplate;
import pro.ivashchuk.employerresearcher.domain.pdf.ResumeTemplate;
import pro.ivashchuk.employerresearcher.repository.JpaCoverLetterRepository;
import pro.ivashchuk.employerresearcher.repository.JpaCoverLetterTemplateRepository;
import pro.ivashchuk.employerresearcher.repository.JpaResumeRepository;
import pro.ivashchuk.employerresearcher.repository.JpaResumeTemplateRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

    public ResponseEntity<InputStreamResource> generateResumePDFById(Long resumeId, Long templateId) throws IOException {
        Resume resume = jpaResumeRepository.findById(resumeId).get();
        ResumeTemplate resumeTemplate = jpaResumeTemplateRepository.findById(templateId).get();
        String file_path = resumeTemplate.preparePDF(resume);
        File file = new File(file_path);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename="
                                + file.getName())
                .contentType(MediaType.APPLICATION_PDF).contentLength(file.length())
                .body(resource);
    }

    public ResponseEntity<InputStreamResource> generateCoverLetterPDFById(Long coverLetterId, Long templateId) throws IOException {
        CoverLetter coverLetter = jpaCoverLetterRepository.findById(coverLetterId).get();
        CoverLetterTemplate coverLetterTemplate =
                jpaCoverLetterTemplateRepository.findById(templateId).get();
        String file_path = coverLetterTemplate.preparePDF(coverLetter);
        File file = new File(file_path);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename="
                                + file.getName())
                .contentType(MediaType.APPLICATION_PDF).contentLength(file.length())
                .body(resource);
    }
}
