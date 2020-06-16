package pro.ivashchuk.employerresearcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.ivashchuk.employerresearcher.domain.Candidate;

@Repository
public interface JpaCandidateRepository extends JpaRepository<Candidate, Long> {

}