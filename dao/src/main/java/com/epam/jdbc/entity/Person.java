package com.epam.jdbc.entity;

import com.epam.springdata.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String email;
    private String password;
    private Gender gender;
}
