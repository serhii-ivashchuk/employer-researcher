package pro.ivashchuk.employerresearcher.domain;

import javax.persistence.*;

@Entity
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String fullName;
    private String address;
    private String email;
    private String webSite;
}
