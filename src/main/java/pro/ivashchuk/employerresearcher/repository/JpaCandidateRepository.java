package pro.ivashchuk.employerresearcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.ivashchuk.employerresearcher.domain.Candidate;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findAll();

    Optional<Candidate> findById(Long id);

    Candidate save(Candidate candidate);

    void delete(Candidate candidate);

    void deleteById(Long id);
}