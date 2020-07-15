package pro.ivashchuk.employerresearcher.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Employer implements Comparable<Employer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PROTECTED)
    private Long id;
    private String name;
    private String fullName;
    private String address;
    private String email;
    private String website;

    public Employer(String name, String fullName, String address, String email, String website) {
        this.name = name;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.website = website;
    }

    @Override
    public int compareTo(Employer employer) {
        return (this.getName().compareTo(employer.getName()));
    }
}
