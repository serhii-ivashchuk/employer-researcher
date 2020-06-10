package pro.ivashchuk.employerresearcher.domain;

import javax.persistence.Entity;

@Entity
public class Candidate implements Comparable<Candidate> {

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
