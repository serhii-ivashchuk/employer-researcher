package pro.ivashchuk.employerresearcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pro.ivashchuk.employerresearcher.service.PDFService;

import java.io.IOException;

@Controller
public class PDFController {

    @Autowired
    PDFService pdfService;

    @GetMapping("/resumes/resume/{id}/generatePDF/template/{templateId}")
    public ResponseEntity<InputStreamResource> generatePDFResume(@PathVariable("id") Long resumeId,
                                                                 @PathVariable("templateId") Long templateId) throws IOException {
        return pdfService.generateResumePDFById(resumeId, templateId);
    }

    @GetMapping("/coverLetters/coverLetter/{id}/generatePDF/template/{templateId}")
    public ResponseEntity<InputStreamResource> generatePDFCoverLetter(@PathVariable("id") Long coverLetterId,
                                                                      @PathVariable("templateId") Long templateId) throws IOException {
        return pdfService.generateCoverLetterPDFById(coverLetterId ,templateId);
    }
}
