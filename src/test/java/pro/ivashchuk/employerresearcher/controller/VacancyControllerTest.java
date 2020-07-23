package pro.ivashchuk.employerresearcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pro.ivashchuk.employerresearcher.domain.Vacancy;
import pro.ivashchuk.employerresearcher.repository.JpaVacancyRepository;

@SpringBootTest
@AutoConfigureMockMvc
class VacancyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JpaVacancyRepository jpaVacancyRepository;

    private Vacancy vacancy;

}