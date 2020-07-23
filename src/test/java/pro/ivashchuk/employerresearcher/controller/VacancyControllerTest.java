package pro.ivashchuk.employerresearcher.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pro.ivashchuk.employerresearcher.domain.Vacancy;
import pro.ivashchuk.employerresearcher.repository.JpaVacancyRepository;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VacancyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JpaVacancyRepository jpaVacancyRepository;

    private Vacancy vacancy;

    @BeforeEach
    public  void setUp() {
        vacancy = new Vacancy(
                "Junior Java Engineer",
                "theBestEmployer.com/vacancies/1",
                "Notes: this position is relevant to my search",
                "theBestEmployer is hiring now! For description please visit our website theBestEmployer" +
                        ".com/vacancies.",
                "Comment: it's very suitable"
        );
    }

    @Test
    public void testVacancyControllerReturnsAllVacanciesPageView() throws Exception {
        mockMvc.perform(get("/vacancies"))
                .andExpect(status().isOk())
                .andExpect(view().name("all_vacancies"))
                .andExpect(content().string(containsString("All Vacancies")));
    }

    @AfterAll
    public void tearDown() {
        jpaVacancyRepository.delete(vacancy);
    }
}