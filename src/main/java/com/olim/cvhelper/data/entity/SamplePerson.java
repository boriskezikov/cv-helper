package com.olim.cvhelper.data.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class SamplePerson extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String occupation;
    private boolean important;

}
