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
public class CoverLetter implements Comparable<CoverLetter> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String date;
    @Column(length = 10_000)
    private String salutation;
    @Column(length = 10_000)
    private String mainPart;
    @Column(length = 10_000)
    private String closing;
    private String signature;

    @JsonIgnore
    @ManyToOne
    private Vacancy vacancy;

    public CoverLetter(String name, String date, String salutation, String mainPart, String closing, String signature) {
        this.name = name;
        this.date = date;
        this.salutation = salutation;
        this.mainPart = mainPart;
        this.closing = closing;
        this.signature = signature;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    @Override
    public int compareTo(CoverLetter coverLetter) {
        return (this.getName().compareTo(coverLetter.getName()));
    }
}