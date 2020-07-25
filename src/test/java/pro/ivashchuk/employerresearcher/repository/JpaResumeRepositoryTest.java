package pro.ivashchuk.employerresearcher.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class JpaResumeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    JpaResumeRepository jpaResumeRepository;
}