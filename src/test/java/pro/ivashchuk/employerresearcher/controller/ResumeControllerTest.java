package pro.ivashchuk.employerresearcher.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pro.ivashchuk.employerresearcher.domain.Resume;
import pro.ivashchuk.employerresearcher.repository.JpaResumeRepository;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ResumeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JpaResumeRepository jpaResumeRepository;

    private Resume resume;

    @BeforeEach
    public  void setUp() {
        resume = new Resume(
        );
    }

    @Test
    public void testResumeControllerReturnsAllResumesPageView() throws Exception {
        mockMvc.perform(get("/resumes"))
                .andExpect(status().isOk())
                .andExpect(view().name("all_resumes"))
                .andExpect(content().string(containsString("All Resumes")));
    }

    @AfterAll
    public void tearDown() {
        jpaResumeRepository.delete(resume);
    }
}