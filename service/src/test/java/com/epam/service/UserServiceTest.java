package com.epam.service;

import com.epam.springdata.entity.Gender;
import com.epam.springdata.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.time.LocalDate;
import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class UserServiceTest {

    @Resource(name = "userService")
    private DefaultService<User> service;
    private User user;
    private static final String FIRST_NAME = "Mikhail";
    private static final String LAST_NAME = "Keiko";
    private static final LocalDate BIRTHDAY = LocalDate.of(1984, 07, 28);
    private static final String EMAIL = "mikhail@yandex.ru";
    private static final String PASSWORD = "password";
    private static final String GENDER = Gender.MAN.name();

    @Before
    public void setUp() {
        user = new User();
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setBirthday(BIRTHDAY);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        user.setGender(GENDER);

        System.out.println(user);
    }

    @Test
    public void crudRepositoryTest() {
        assertFalse(service.getAll().contains(user));
        //user.setId(service.save(user).getId());
        //assertTrue(Objects.nonNull(user.getId()));

    }
}
