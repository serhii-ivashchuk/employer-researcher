package pro.ivashchuk.employerresearcher.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pro.ivashchuk.employerresearcher.domain.CoverLetter;
import pro.ivashchuk.employerresearcher.repository.JpaCoverLetterRepository;

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
}