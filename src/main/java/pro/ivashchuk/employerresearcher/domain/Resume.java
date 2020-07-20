package pro.ivashchuk.employerresearcher.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Resume implements Comparable<Resume> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String date;
    @Column(length = 10_000)
    private String summary;
    @Column(length = 10_000)
    private String skills;
    @Column(length = 10_000)
    private String projects;
    private String experience;
    @Column(length = 10_000)
    private String education;
    @Column(length = 10_000)
    private String additionalInformation;

    @JsonIgnore
    @ManyToOne
    private Vacancy vacancy;

    public Resume(String name, String date, String summary, String skills, String projects, String education, String additionalInformation) {
        this.name = name;
        this.date = date;
        this.summary = summary;
        this.skills = skills;
        this.projects = projects;
        this.education = education;
        this.additionalInformation = additionalInformation;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    @Override
    public int compareTo(Resume resume) {
        return (this.getName().compareTo(resume.getName()));
    }
}