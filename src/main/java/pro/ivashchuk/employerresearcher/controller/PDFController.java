package pro.ivashchuk.employerresearcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pro.ivashchuk.employerresearcher.service.PDFService;

@Controller
public class PDFController {

    @Autowired
    PDFService pdfService;
}
