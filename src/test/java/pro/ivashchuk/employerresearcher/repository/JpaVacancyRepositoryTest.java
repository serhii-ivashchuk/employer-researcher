package pro.ivashchuk.employerresearcher.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pro.ivashchuk.employerresearcher.domain.Vacancy;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class JpaVacancyRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    JpaVacancyRepository jpaVacancyRepository;

    @Test
    public void shouldFindAllVacancies() {
        Vacancy testVacancy1 = getVacancy("Junior Java Engineer");
        Vacancy testVacancy2 = getVacancy("Java Engineer");
        Vacancy testVacancy3 = getVacancy("Senior Java Engineer");
        List<Vacancy> initialEmployers = Arrays.asList(testVacancy1, testVacancy2, testVacancy3);
        entityManager.persist(testVacancy1);
        entityManager.persist(testVacancy2);
        entityManager.persist(testVacancy3);
        entityManager.flush();
        int vacanciesQuantity = jpaVacancyRepository.findAll().size();
        List<Vacancy> foundVacancies = jpaVacancyRepository.findAll();

        assertEquals(foundVacancies.size(), vacanciesQuantity, "When repository stores 3 vacancies, then it " +
                "should return list of 3 Vacancies");
    }

    @Test
    public void shouldFindEmployerById() {
        Vacancy testVacancy = getVacancy("Junior Java Engineer");
        entityManager.persist(testVacancy);
        Long testVacancyId = (Long) entityManager.getId(testVacancy);
        entityManager.flush();
        Vacancy foundVacancy = jpaVacancyRepository.findById(testVacancy.getId()).get();

        assertEquals(foundVacancy.getPosition(), testVacancy.getPosition());
    }

    private Vacancy getVacancy(String position) {
        return new Vacancy(
                position,
                "theBestEmployer.com/vacancies/1",
                "Notes: this position is relevant to my search",
                "email@theBestEmployer.com",
                "Comment: it's very suitable");
    }
}