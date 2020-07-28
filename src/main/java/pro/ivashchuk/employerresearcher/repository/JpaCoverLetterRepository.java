package pro.ivashchuk.employerresearcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.ivashchuk.employerresearcher.domain.CoverLetter;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCoverLetterRepository extends JpaRepository<CoverLetter, Long> {

    List<CoverLetter> findAll();

    Optional<CoverLetter> findById(Long id);

    CoverLetter save(CoverLetter coverLetter);

    void delete(CoverLetter coverLetter);

    void deleteById(Long id);
}
