package com.epam.service;

import com.epam.DefaultDao;
import com.epam.jdbc.entity.Person;
import com.epam.service.jdbc.impl.PersonServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PeopleServiceTest {

    private PersonServiceImpl service;
    private DefaultDao defaultDao;
    private Person person;
    private Integer id = 1;
    private String name = "test";
    private LocalDate birthday = LocalDate.of(1980, 10, 12);

    @Before
    public void setUp() {
        defaultDao = mock(DefaultDao.class);
        service = spy(PersonServiceImpl.class);
        person = new Person();

        service.setDao(defaultDao);
        person.setId(id);
        person.setName(name);
        person.setBirthday(birthday);
    }

    @Test
    public void savePersonTest() {
        person.setId(null);
        when(defaultDao.create(any(Person.class))).thenReturn(person);
        assertEquals(service.save(person), person);
    }

    @Test
    public void updatePersonTest() {
        person.setId(id);
        when(defaultDao.update(any(Person.class))).thenReturn(person);
        assertEquals(service.save(person), person);
    }

    @Test
    public void getAllPersonTest() {
        when(defaultDao.getAll()).thenReturn(Arrays.asList(person));
        assertEquals(service.getAll(), Arrays.asList(person));
    }

    @Test
    public void getPersonByIdTest() {
        person.setId(id);
        when(defaultDao.getById(any(Integer.class))).thenReturn(person);
        assertEquals(service.getById(person.getId()), person);
    }

    @Test
    public void deletePersonTest() {
        service.delete(person);
        verify(defaultDao).delete(any(Person.class));
    }
}
