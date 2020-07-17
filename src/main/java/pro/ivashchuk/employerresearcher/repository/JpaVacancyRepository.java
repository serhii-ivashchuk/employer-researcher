package pro.ivashchuk.employerresearcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.ivashchuk.employerresearcher.domain.Vacancy;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaVacancyRepository extends JpaRepository<Vacancy, Long> {

    List<Vacancy> findAll();

    Optional<Vacancy> findById(Long id);

    Vacancy save(Vacancy vacancy);

    void delete(Vacancy vacancy);

    void deleteById(Long id);
}