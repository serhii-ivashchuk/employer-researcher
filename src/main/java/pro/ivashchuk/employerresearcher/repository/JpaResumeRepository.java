package pro.ivashchuk.employerresearcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.ivashchuk.employerresearcher.domain.Resume;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaResumeRepository extends JpaRepository<Resume, Long> {

        List<Resume> findAll();

        Optional<Resume> findById(Long id);

        Resume save(Resume resume);

        void delete(Resume resume);

        void deleteById(Long id);
}
