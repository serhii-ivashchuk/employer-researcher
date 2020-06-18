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
    public void shouldFindAllEmployers() {
        Employer testEmployer1 = getEmployer("TheBestEmployer 1", "Marienplatz, 1, Munich, Germany");
        Employer testEmployer2 = getEmployer("TheBestEmployer 2", "Marienplatz, 2, Munich, Germany");
        Employer testEmployer3 = getEmployer("TheBestEmployer 3", "Marienplatz, 3, Munich, Germany");
        List<Employer> initialEmployers = Arrays.asList(testEmployer1, testEmployer2, testEmployer3);
        entityManager.persist(testEmployer1);
        entityManager.persist(testEmployer2);
        entityManager.persist(testEmployer3);
        entityManager.flush();
        int employersQuantity = jpaEmployerRepository.findAll().size();
        List<Employer> foundEmployers = jpaEmployerRepository.findAll();

        assertEquals(foundEmployers.size(), employersQuantity, "When repository stores 3 employers, then it " +
                "should return list of 3 Employers");
    }

    private Employer getEmployer(String name, String address) {
        return new Employer(
                name,
                "TheBestEmployer Aktiengesellschaft",
                address,
                "email@theBestEmployer.com",
                "theBestEmployer.com");
    }
}