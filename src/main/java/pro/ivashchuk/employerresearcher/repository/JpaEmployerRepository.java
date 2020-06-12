package pro.ivashchuk.employerresearcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.ivashchuk.employerresearcher.domain.Employer;

@Repository
public interface JpaEmployerRepository extends JpaRepository<Employer, Long> {
}
