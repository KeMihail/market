package com.epam.service.jdbc.impl;

import com.epam.DefaultDao;
import com.epam.jdbc.entity.Person;
import com.epam.service.DefaultService;
import lombok.Setter;

import javax.annotation.Resource;
import java.util.List;

public class PersonServiceImpl implements DefaultService<Person> {

    @Setter
    @Resource(name = "personDao")
    private DefaultDao dao;

    @Override
    public Person getById(Integer id) {
        return (Person) dao.getById(id);
    }

    @Override
    public List<Person> getAll() {
        return dao.getAll();
    }

    @Override
    public Person save(Person person) {
        if (person.getId() == null) {
            dao.create(person);
        } else {
            dao.update(person);
        }
        return person;
    }

    @Override
    public void delete(Person person) {
        dao.delete(person);
    }
}
