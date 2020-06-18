package pro.ivashchuk.employerresearcher.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class JpaEmployerRepositoryTest {

    @Autowired
    JpaEmployerRepository jpaEmployerRepository;

    @Test
    public void shouldFindEmployerById() {}

}