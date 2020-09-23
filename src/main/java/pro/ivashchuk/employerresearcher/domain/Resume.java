package pro.ivashchuk.employerresearcher.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
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
    @Setter(AccessLevel.PROTECTED)
    private Long id;
    private String name;
    private String date;
    private String summary;
    private String skills;
    private String projects;
    private String experience;
    private String education;
    private String additionalInformation;

    @JsonIgnore
    @ManyToOne
    private Vacancy vacancy;

    public Resume(String name, String date, String summary, String skills, String projects, String experience, String education, String additionalInformation) {
        this.name = name;
        this.date = date;
        this.summary = summary;
        this.skills = skills;
        this.projects = projects;
        this.experience = experience;
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