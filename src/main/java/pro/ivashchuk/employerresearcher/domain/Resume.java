package pro.ivashchuk.employerresearcher.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String date;
    private String summary;
    private String skills;
    private String projects;
    private String experience;
    private String education;
    private String additionalInformation;

}