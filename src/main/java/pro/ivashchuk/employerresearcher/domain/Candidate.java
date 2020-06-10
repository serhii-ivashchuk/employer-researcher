package pro.ivashchuk.employerresearcher.domain;

import javax.persistence.Entity;

@Entity
public class Candidate {

    private String name;
    private String surname;
    private String address;
    private String github;
    private String email;
    private String phone;
}
