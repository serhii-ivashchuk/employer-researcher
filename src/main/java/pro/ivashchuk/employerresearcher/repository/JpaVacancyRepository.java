package pro.ivashchuk.employerresearcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.ivashchuk.employerresearcher.domain.Vacancy;

@Repository
public interface JpaVacancyRepository extends JpaRepository<Vacancy, Long> {
}