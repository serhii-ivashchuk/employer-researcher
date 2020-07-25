package pro.ivashchuk.employerresearcher.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pro.ivashchuk.employerresearcher.domain.Resume;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class JpaResumeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    JpaResumeRepository jpaResumeRepository;

    @Test
    public void shouldFindAllResumes() {
        Resume testResume1 = getResume("John Doe, Junior Java Developer", "25, July 2020");
        Resume testResume2 = getResume("John Smith, Java Developer", "25, July 2020");
        Resume testResume3 = getResume("John Doe, Junior Java Developer", "25, July 2020");
        entityManager.persist(testResume1);
        entityManager.persist(testResume2);
        entityManager.persist(testResume3);
        entityManager.flush();
        int employersQuantity = jpaResumeRepository.findAll().size();
        List<Resume> foundResumes = jpaResumeRepository.findAll();

        assertEquals(foundResumes.size(), employersQuantity, "When repository stores 3 Resumes, then it " +
                "should return list of 3 Resumes");
    }

    private Resume getResume(String name, String date) {
        return new Resume(
                name,
                date,
                "Summary",
                "Skills",
                "Projects",
                "Exprerience",
                "Education",
                "Additional Information");
    }
}