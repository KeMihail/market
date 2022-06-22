package com.epam.service.hibernate.impl;

import com.epam.DefaultDao;
import com.epam.jpa.entity.People;
import com.epam.service.DefaultService;
import lombok.Setter;

import javax.annotation.Resource;
import java.util.List;

public class PeopleServiceImpl implements DefaultService<People> {

    @Setter
    @Resource(name = "peopleDao")
    private DefaultDao dao;

    @Override
    public People getById(Integer id) {
        return (People) dao.getById(id);
    }

    @Override
    public List<People> getAll() {
        return dao.getAll();
    }

    @Override
    public People save(People people) {
        if (people.getId() == null) {
            dao.create(people);
        } else {
            dao.update(people);
        }
        return people;
    }

    @Override
    public void delete(People people) {
        dao.delete(people);
    }
}
