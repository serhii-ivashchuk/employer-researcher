package pro.ivashchuk.employerresearcher.repository;

import org.springframework.stereotype.Repository;
import pro.ivashchuk.employerresearcher.domain.Resume;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaResumeRepository {

        List<Resume> findAll();

        Optional<Resume> findById(Long id);

        Resume save(Resume resume);

        void delete(Resume resume);

        void deleteById(Long id);
}
