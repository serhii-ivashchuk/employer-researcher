package pro.ivashchuk.employerresearcher.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pro.ivashchuk.employerresearcher.domain.Employer;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class JpaEmployerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    JpaEmployerRepository jpaEmployerRepository;

    @Test
    public void shouldFindEmployerById() {
        // given
        Employer testEmployer = new Employer(
                "TheBestEmployer AG",
                "TheBestEmployer Aktiengesellschaft",
                "Marienplatz, 1, Munich, Germany",
                "email@theBestEmployer.com",
                "theBestEmployer.com");
        entityManager.persist(testEmployer);
        Long testEmployerId = (Long) entityManager.getId(testEmployer);
        entityManager.flush();
        // when
        Employer foundEmployer = jpaEmployerRepository.findById(testEmployer.getId()).get();
        // then
        assertEquals(foundEmployer.getName(), testEmployer.getName());
    }
}