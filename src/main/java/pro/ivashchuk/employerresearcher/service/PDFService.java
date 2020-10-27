package pro.ivashchuk.employerresearcher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.ivashchuk.employerresearcher.repository.JpaResumeRepository;

@Service
public class PDFService {

    @Autowired
    private JpaResumeRepository jpaResumeRepository;
}
