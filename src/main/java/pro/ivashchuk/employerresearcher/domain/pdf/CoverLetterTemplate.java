package pro.ivashchuk.employerresearcher.domain.pdf;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pro.ivashchuk.employerresearcher.domain.CoverLetter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CoverLetterTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    private String name;

    public CoverLetterTemplate(String name) {
        this.name = name;
    }

    public String preparePDF(CoverLetter coverLetter) {
        return "";
    }
}
