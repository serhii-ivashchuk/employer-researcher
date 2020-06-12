package pro.ivashchuk.employerresearcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.ivashchuk.employerresearcher.domain.Employer;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaEmployerRepository extends JpaRepository<Employer, Long> {

    List<Employer> findAll();

    Optional<Employer> findById(Long id);

    Employer save(Employer Employer);

    void delete(Employer Employer);
}
