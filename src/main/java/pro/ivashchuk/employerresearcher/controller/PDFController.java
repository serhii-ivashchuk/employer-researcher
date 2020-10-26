package pro.ivashchuk.employerresearcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pro.ivashchuk.employerresearcher.service.PDFService;

import java.io.IOException;
import java.net.URISyntaxException;

@Controller
public class PDFController {

    @Autowired
    PDFService pdfService;

    @GetMapping("/resumes/resume/{id}/generatePDF")
    public ResponseEntity<InputStreamResource> generatePDFResume(@PathVariable("id") Long id) throws IOException, URISyntaxException {
         return pdfService.generateResumePDFById(id);
    }

    @GetMapping("/coverLetters/coverLetter/{id}/generatePDF")
    public ResponseEntity<InputStreamResource> generatePDFCoverLetter(@PathVariable("id") Long id) throws IOException,
            URISyntaxException {
        return pdfService.generateCoverLetterPDFById(id);
    }
}
