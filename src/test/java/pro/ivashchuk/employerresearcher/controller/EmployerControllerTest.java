package pro.ivashchuk.employerresearcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pro.ivashchuk.employerresearcher.domain.Employer;
import pro.ivashchuk.employerresearcher.repository.JpaEmployerRepository;

@SpringBootTest
@AutoConfigureMockMvc
class EmployerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JpaEmployerRepository jpaEmployerRepository;

    private Employer employer;
}