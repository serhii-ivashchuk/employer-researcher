package pro.ivashchuk.employerresearcher.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pro.ivashchuk.employerresearcher.domain.CoverLetter;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class JpaCoverLetterRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    JpaCoverLetterRepository jpaCoverLetterRepository;

    @Test
    public void shouldFindAllCoverLetters() {
        CoverLetter testCoverLetter1 = getCoverLetter("John Doe, Junior Java Engineer", "30, July 2020");
        CoverLetter testCoverLetter2 = getCoverLetter("John Doe, Java Engineer", "30, July 2020");
        CoverLetter testCoverLetter3 = getCoverLetter("John Doe, Senior Java Engineer", "30, July 2020");
        entityManager.persist(testCoverLetter1);
        entityManager.persist(testCoverLetter2);
        entityManager.persist(testCoverLetter3);
        entityManager.flush();
        int coverLettersQuantity = jpaCoverLetterRepository.findAll().size();
        List<CoverLetter> foundCoverLetters = jpaCoverLetterRepository.findAll();

        assertEquals(foundCoverLetters.size(), coverLettersQuantity, "When repository stores 3 coverLetters, then it " +
                "should return list of 3 CoverLetters");
    }

    @Test
    public void shouldFindCoverLetterById() {
        CoverLetter testCoverLetter = getCoverLetter("John Doe, Junior Java Engineer", "30, July 2020");
        entityManager.persist(testCoverLetter);
        Long testCoverLetterId = (Long) entityManager.getId(testCoverLetter);
        entityManager.flush();
        CoverLetter foundCoverLetter = jpaCoverLetterRepository.findById(testCoverLetterId).get();

        assertEquals(foundCoverLetter.getName(), testCoverLetter.getName());
    }

    @Test
    public void shouldSave() {
        CoverLetter testCoverLetter = getCoverLetter("John Doe, Junior Java Engineer", "30, July 2020");
        assertThat(testCoverLetter, is(notNullValue()));
        entityManager.persist(testCoverLetter);
        entityManager.flush();
        CoverLetter savedCoverLetter = jpaCoverLetterRepository.save(testCoverLetter);

        assertThat(savedCoverLetter, is(notNullValue()));
        assertEquals(testCoverLetter, savedCoverLetter, "Test coverLetter should be saved");
    }

    @Test
    public void shouldUpdateCoverLetter() {
        CoverLetter coverLetterBeforeUpdate = jpaCoverLetterRepository.save(getCoverLetter("oldName", "oldDate"));
        CoverLetter coverLetterAfterUpdate =
                jpaCoverLetterRepository.save(createUpdatedCoverLetter(coverLetterBeforeUpdate, "newName"));

        assertEquals("newName", coverLetterAfterUpdate.getName(), "Names should be equal");
        assertEquals(coverLetterBeforeUpdate.getId(), coverLetterAfterUpdate.getId(), "Id's should be equal");
        assertEquals(coverLetterBeforeUpdate.getParagraphs(), coverLetterAfterUpdate.getParagraphs(), "Paragraphs " +
                "should be " +
                "equal");
    }

    @Test
    public void shouldDelete() {
        CoverLetter testCoverLetter = getCoverLetter("John Doe, Junior Java Engineer", "30, July 2020");
        entityManager.persist(testCoverLetter);
        entityManager.flush();
        jpaCoverLetterRepository.delete(testCoverLetter);

        assertEquals(jpaCoverLetterRepository.findAll().size(), 0, "The repository should have 0 CoverLetter entity");
    }

    private CoverLetter getCoverLetter(String name, String date) {
        return new CoverLetter(name, date, "Salutation", new String[]{"First", "Second", "Third"}, "Closing",
                "Signature");
    }

    private CoverLetter createUpdatedCoverLetter(CoverLetter coverLetter, String newName) {
        coverLetter.setName(newName);
        return coverLetter;
    }
}