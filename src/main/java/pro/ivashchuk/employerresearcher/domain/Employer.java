package pro.ivashchuk.employerresearcher.domain;

import javax.persistence.*;

@Entity
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
