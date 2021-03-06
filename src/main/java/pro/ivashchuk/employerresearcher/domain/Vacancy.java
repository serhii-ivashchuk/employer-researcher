package pro.ivashchuk.employerresearcher.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vacancy implements Comparable<Vacancy> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String position;
    private String link;
    private String notes;
    private String description;
    private String comment;

    @JsonIgnore
    @ManyToOne
    private Employer employer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vacancy", fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Resume> resumes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vacancy", fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<CoverLetter> coverLetters = new ArrayList<>();

    public Vacancy(String position, String link, String notes, String description, String comment) {
        this.position = position;
        this.link = link;
        this.notes = notes;
        this.description = description;
        this.comment = comment;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Resume> getResumes() {
        return resumes;
    }

    public List<CoverLetter> getCoverLetters() {
        return coverLetters;
    }

    public Resume addResumeToResumes(Resume resume) {
        resumes.add(resume);
        return resume;
    }

    public Resume deleteResumeFromResumes(Resume resume) {
        resumes.remove(resume);
        return resume;
    }

    public CoverLetter addCoverLetterToCoverLetters(CoverLetter coverLetter) {
        coverLetters.add(coverLetter);
        return coverLetter;
    }

    public CoverLetter deleteCoverLetterFromCoverLetters(CoverLetter coverLetter) {
        coverLetters.remove(coverLetter);
        return coverLetter;
    }

    @Override
    public int compareTo(Vacancy vacancy) {
        return (this.getPosition().compareTo(vacancy.getPosition()));
    }
}