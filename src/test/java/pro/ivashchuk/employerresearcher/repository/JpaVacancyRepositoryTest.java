package pro.ivashchuk.employerresearcher.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class JpaVacancyRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    JpaVacancyRepository jpaVacancyRepository;

}