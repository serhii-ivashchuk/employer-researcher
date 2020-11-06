package pro.ivashchuk.employerresearcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.ivashchuk.employerresearcher.domain.pdf.ResumeTemplate;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaResumeTemplateRepository extends JpaRepository<ResumeTemplate, Long> {

        List<ResumeTemplate> findAll();

        Optional<ResumeTemplate> findById(Long id);

        ResumeTemplate save(ResumeTemplate resumeTemplate);

        void delete(ResumeTemplate resumeTemplate);

        void deleteById(Long id);
}
