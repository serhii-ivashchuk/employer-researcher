package pro.ivashchuk.employerresearcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ivashchuk.employerresearcher.domain.Candidate;

public interface JpaCandidateRepository extends JpaRepository<Candidate, Long> {

}