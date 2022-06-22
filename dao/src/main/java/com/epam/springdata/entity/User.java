package com.epam.springdata.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthday;
    private String email;
    private String password;
    private Gender gender;

    public void setGender(String gender) {
        this.gender = Gender.valueOf(gender);
    }

    public String getGender() {
        return gender.name();
    }
}
