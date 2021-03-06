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

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
    public void testCoverLetterControllerReturnsAllCoverLettersPageView() throws Exception {
        mockMvc.perform(get("/coverLetters"))
                .andExpect(status().isOk())
                .andExpect(view().name("all_cover_letters"))
                .andExpect(content().string(containsString("All Cover Letters")));
    }

    @Test
    public void testCoverLetterControllerReturnsAddCoverLetterPageView() throws Exception {
        mockMvc.perform(get("/coverLetters/addNewCoverLetter"))
                .andExpect(status().isOk())
                .andExpect(view().name("add_cover_letter"))
                .andExpect(content().string(containsString("Add New Cover Letter")));
    }

    @Test
    public void testCoverLetterControllerReturnsCoverLetterPageView() throws Exception {
        jpaCoverLetterRepository.save(coverLetter);
        List<CoverLetter> all = jpaCoverLetterRepository.findAll();
        CoverLetter coverLetterFromRepository = all.get(0);
        mockMvc.perform(get("/coverLetters/coverLetter/" + coverLetterFromRepository.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("cover_letter"))
                .andExpect(content().string(containsString("Cover Letter")));
    }

    @Test
    public void testCoverLetterControllerShouldRedirectAfterCoverLetterDeletion() throws Exception {
        jpaCoverLetterRepository.save(coverLetter);
        List<CoverLetter> all = jpaCoverLetterRepository.findAll();
        CoverLetter coverLetterFromRepository = all.get(0);
        mockMvc.perform(delete("/coverLetters/coverLetter/" + coverLetterFromRepository.getId() + "/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/coverLetters"))
                .andExpect(content().string(containsString("")));
    }

    @AfterAll
    public void tearDown() {
        jpaCoverLetterRepository.delete(coverLetter);
    }
}