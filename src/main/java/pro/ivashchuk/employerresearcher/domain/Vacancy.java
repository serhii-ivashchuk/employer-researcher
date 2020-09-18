package pro.ivashchuk.employerresearcher.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vacancy implements Comparable<Vacancy> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PROTECTED)
    private Long id;
    private String position;
    private String link;
    private String notes;
    private String description;
    private String comment;

    @ManyToOne
    private Employer employer;

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

    @Override
    public int compareTo(Vacancy vacancy) {
        return (this.getPosition().compareTo(vacancy.getPosition()));
    }
}