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

import java.util.List;

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

    @Test
    public void testResumeControllerReturnsAddResumePageView() throws Exception {
        mockMvc.perform(get("/resumes/addNewResume"))
                .andExpect(status().isOk())
                .andExpect(view().name("add_resume"))
                .andExpect(content().string(containsString("Add New Resume")));
    }

    @Test
    public void testResumeControllerReturnsResumePageView() throws Exception {
        jpaResumeRepository.save(resume);
        List<Resume> all = jpaResumeRepository.findAll();
        Resume resumeFromRepository = all.get(0);
        mockMvc.perform(get("/resumes/resume/" + resumeFromRepository.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("resume"))
                .andExpect(content().string(containsString("Resume")));
    }

    @AfterAll
    public void tearDown() {
        jpaResumeRepository.delete(resume);
    }
}