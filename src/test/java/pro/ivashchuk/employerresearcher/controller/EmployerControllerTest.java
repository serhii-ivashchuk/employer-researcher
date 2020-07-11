package pro.ivashchuk.employerresearcher.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pro.ivashchuk.employerresearcher.domain.Employer;
import pro.ivashchuk.employerresearcher.repository.JpaEmployerRepository;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmployerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JpaEmployerRepository jpaEmployerRepository;

    private Employer employer;

    @BeforeEach
    public  void setUp() {
        employer = new Employer(
                "TheBestEmployer 1",
                "TheBestEmployer Aktiengesellschaft",
                "Marienplatz, 1, Munich, Germany",
                "email@theBestEmployer.com",
                "theBestEmployer.com");
    }

    @Test
    public void testEmployerControllerReturnsAllEmployersPageView() throws Exception {
        mockMvc.perform(get("/employers"))
                .andExpect(status().isOk())
                .andExpect(view().name("all_employers"))
                .andExpect(content().string(containsString("All Employers")));
    }
}