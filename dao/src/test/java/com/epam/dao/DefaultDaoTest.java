package com.epam.dao;

import com.epam.DefaultDao;
import com.epam.jdbc.entity.Person;
import com.epam.springdata.entity.Gender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dao-context.xml")
@ActiveProfiles("test")
public class DefaultDaoTest {

    @Resource(name = "personDao")
    private DefaultDao dao;
    private Person person;
    private static final String FIRST_NAME = "mikhail";
    private static final String LAST_NAME = "keiko";
    private static final LocalDate BIRTHDAY = LocalDate.of(1984, 7, 28);
    private static final String EMAIL = "mikhaila@yandex.ru";
    private static final String PASSWORD = "123";
    private static final Gender GENDER = Gender.MAN;
    private static final String NAME = "mihail";

    @Before
    public void setUp() {
        person = new Person();
        person.setFirstName(FIRST_NAME);
        person.setLastName(LAST_NAME);
        person.setBirthday(BIRTHDAY);
        person.setEmail(EMAIL);
        person.setPassword(PASSWORD);
        person.setGender(GENDER);
    }

    @Test
    public void crudPersonTest() {

        // required use test db !!!
        assertTrue(Objects.isNull(dao.getById(1)));
        assertTrue(dao.getAll().isEmpty());

        // test create
        dao.create(person);
        assertNotNull(person.getId());

        // test getById
        assertEquals(dao.getById(person.getId()), person);

        // test update
        person.setFirstName(NAME);
        dao.update(person);
        assertEquals(dao.getById(person.getId()), person);

        // test getAll
        assertTrue(dao.getAll().contains(person));

        // test delete
        dao.delete(person);
        assertFalse(dao.getAll().contains(person));
    }
}
