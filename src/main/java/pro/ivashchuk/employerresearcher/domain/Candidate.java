package pro.ivashchuk.employerresearcher.domain;

import lombok.AccessLevel;
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
public class Candidate implements Comparable<Candidate> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PROTECTED)
    private Long id;
    private String name;
    private String surname;
    private String address;
    private String github;
    private String email;
    private String phone;

    public Candidate(Long id, String name, String surname, String address,  String github, String email, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.github = github;
        this.email = email;
        this.phone = phone;
    }

    public Candidate(String name, String surname, String address,  String github, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.github = github;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public int compareTo(Candidate candidate) {
        return (this.getName().compareTo(candidate.getName()));
    }
}
