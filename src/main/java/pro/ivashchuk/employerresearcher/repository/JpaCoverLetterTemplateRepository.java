package pro.ivashchuk.employerresearcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.ivashchuk.employerresearcher.domain.pdf.CoverLetterTemplate;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCoverLetterTemplateRepository extends JpaRepository<CoverLetterTemplate, Long> {

        List<CoverLetterTemplate> findAll();

        Optional<CoverLetterTemplate> findById(Long id);

        CoverLetterTemplate save(CoverLetterTemplate coverLetterTemplate);

        void delete(CoverLetterTemplate coverLetterTemplate);

        void deleteById(Long id);
}
