package com.olim.cvhelper.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.olim.cvhelper.data.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "application_users")
public class User extends AbstractEntity {

    private String username;
    private String name;
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

}
