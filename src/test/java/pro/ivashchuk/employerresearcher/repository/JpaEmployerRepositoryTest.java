package pro.ivashchuk.employerresearcher.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pro.ivashchuk.employerresearcher.domain.Employer;

import java.util.Arrays;
import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
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

    @Test
    public void shouldFindEmployerById() {
        Employer testEmployer = getEmployer("TheBestEmployer AG", "Marienplatz, 1, Munich, Germany");
        entityManager.persist(testEmployer);
        Long testEmployerId = (Long) entityManager.getId(testEmployer);
        entityManager.flush();
        Employer foundEmployer = jpaEmployerRepository.findById(testEmployer.getId()).get();

        assertEquals(foundEmployer.getName(), testEmployer.getName());
    }

    @Test
    public void shouldSave() {
        Employer testEmployer = getEmployer("TheBestEmployer AG", "Marienplatz, 1, Munich, Germany");
        assertThat(testEmployer, is(notNullValue()));
        entityManager.persist(testEmployer);
        entityManager.flush();
        Employer savedEmployer = jpaEmployerRepository.save(testEmployer);

        assertThat(savedEmployer, is(notNullValue()));
        assertEquals(testEmployer, savedEmployer, "Test employer should be saved");
    }

    @Test
    public void shouldUpdateEmployer() {
        Employer employerBeforeUpdate = jpaEmployerRepository.save(getEmployer("oldName", "oldAddress"));
        Employer employerAfterUpdate = jpaEmployerRepository.save(createUpdatedEmployer(employerBeforeUpdate, "Super " +
                "Employer"));

        assertEquals("Super Employer", employerAfterUpdate.getName(), "Names should be equal");
        assertEquals(employerBeforeUpdate.getId(), employerAfterUpdate.getId(), "Id's should be equal");
        assertEquals(employerBeforeUpdate.getAddress(), employerAfterUpdate.getAddress(), "Addresses should be equal");
    }

    @Test
    public void shouldDelete() {
        Employer testEmployer = getEmployer("TheBestEmployer AG", "Marienplatz, 1, Munich, Germany");
        entityManager.persist(testEmployer);
        entityManager.flush();
        jpaEmployerRepository.delete(testEmployer);

        assertEquals(jpaEmployerRepository.findAll().size(), 0, "The repository should have 0 Employer entity");
    }

    @Test
    public void shouldDeleteById() {
        Employer testEmployer = getEmployer("TheBestEmployer AG", "Marienplatz, 1, Munich, Germany");
        entityManager.persist(testEmployer);
        entityManager.flush();
        Long testEmployerId = (Long) entityManager.getId(testEmployer);
        jpaEmployerRepository.deleteById(testEmployerId);

        assertEquals(jpaEmployerRepository.findAll().size(), 0, "The repository after deletion should have 0 Employer" +
                " entity");
    }

    private Employer getEmployer(String name, String address) {
        return new Employer(
                name,
                "TheBestEmployer Aktiengesellschaft",
                address,
                "email@theBestEmployer.com",
                "theBestEmployer.com");
    }

    private Employer createUpdatedEmployer(Employer employer, String newName) {
        employer.setName(newName);
        return employer;
    }
}