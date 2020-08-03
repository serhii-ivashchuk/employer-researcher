package pro.ivashchuk.employerresearcher.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pro.ivashchuk.employerresearcher.domain.CoverLetter;
import pro.ivashchuk.employerresearcher.repository.JpaCoverLetterRepository;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CoverLetterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JpaCoverLetterRepository jpaCoverLetterRepository;

    private CoverLetter coverLetter;

    @BeforeEach
    public  void setUp() {
        coverLetter = new CoverLetter(
        );
    }

    @Test
    public void testCoverLetterControllerReturnsAllVacanciesPageView() throws Exception {
        mockMvc.perform(get("/coverLetters"))
                .andExpect(status().isOk())
                .andExpect(view().name("all_cover_letters"))
                .andExpect(content().string(containsString("All Cover Letters")));
    }

    @AfterAll
    public void tearDown() {
        jpaCoverLetterRepository.delete(coverLetter);
    }
}