package pro.ivashchuk.employerresearcher.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pro.ivashchuk.employerresearcher.domain.Resume;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
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
    
    @Test
    public void shouldFindResumeById() {
        Resume testResume = getResume("John Doe, Junior Java Developer", "25, July 2020");
        entityManager.persist(testResume);
        Long testResumeId = (Long) entityManager.getId(testResume);
        entityManager.flush();
        Resume foundResume = jpaResumeRepository.findById(testResumeId).get();

        assertEquals(foundResume.getName(), testResume.getName());
    }

    @Test
    public void shouldSave() {
        Resume testResume = getResume("John Doe, Junior Java Developer", "25, July 2020");
        assertThat(testResume, is(notNullValue()));
        entityManager.persist(testResume);
        entityManager.flush();
        Resume savedResume = jpaResumeRepository.save(testResume);

        assertThat(savedResume, is(notNullValue()));
        assertEquals(testResume, savedResume, "Test resume should be saved");
    }
    
    @Test
    public void shouldUpdateResume() {
        Resume resumeBeforeUpdate = jpaResumeRepository.save(getResume("oldName", "oldDate"));
        Resume resumeAfterUpdate = jpaResumeRepository.save(createUpdatedResume(resumeBeforeUpdate, "newName"));

        assertEquals("newName", resumeAfterUpdate.getName(), "Names should be equal");
        assertEquals(resumeBeforeUpdate.getId(), resumeAfterUpdate.getId(), "Id's should be equal");
        assertEquals(resumeBeforeUpdate.getSummary(), resumeAfterUpdate.getSummary(), "Summaries should be equal");
    }
    
    @Test
    public void shouldDelete() {
        Resume testResume = getResume("John Doe, Junior Java Developer", "25, July 2020");
        entityManager.persist(testResume);
        entityManager.flush();
        jpaResumeRepository.delete(testResume);

        assertEquals(jpaResumeRepository.findAll().size(), 0, "The repository should have 0 Resume entity");
    }

    @Test
    public void shouldDeleteById() {
        Resume testResume = getResume("John Doe, Junior Java Developer", "25, July 2020");
        entityManager.persist(testResume);
        entityManager.flush();
        Long testResumeId = (Long) entityManager.getId(testResume);
        jpaResumeRepository.deleteById(testResumeId);

        assertEquals(jpaResumeRepository.findAll().size(), 0, "The repository after deletion should have 0 Resume" +
                " entity");
    }
    
    private Resume getResume(String name, String date) {
        return new Resume(
                name,
                date,
                "Summary",
                "Skills",
                "Projects",
                "Experience",
                "Education",
                "Additional Information");
    }

    private Resume createUpdatedResume(Resume resume, String newName) {
        resume.setName(newName);
        return resume;
    }
}