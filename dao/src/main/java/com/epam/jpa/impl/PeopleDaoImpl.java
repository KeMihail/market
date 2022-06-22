package com.epam.jpa.impl;

import com.epam.DefaultDao;
import com.epam.jpa.HibernateUtilOldVersion;
import com.epam.jpa.entity.People;
import org.hibernate.Session;

import java.util.List;

public class PeopleDaoImpl implements DefaultDao<People> {

    @Override
    public People getById(Integer id) {
        //Session session = HibernateUtil.getSessionFactory().openSession();
        Session session = HibernateUtilOldVersion.getSession();
        session.beginTransaction();
        People people = session.get(People.class, id);
        session.flush();
        session.close();
        return people;
    }

    @Override
    public List<People> getAll() {
        //Session session = HibernateUtil.getSessionFactory().openSession();
        Session session = HibernateUtilOldVersion.getSession();
        return session.createCriteria(People.class).list();
    }

    @Override
    public People create(People people) {
        //Session session = HibernateUtil.getSessionFactory().openSession();
        Session session = HibernateUtilOldVersion.getSession();
        session.beginTransaction();
        session.save(people);
        session.flush();
        session.close();
        return people;
    }

    @Override
    public People update(People people) {
        //Session session = HibernateUtil.getSessionFactory().openSession();
        Session session = HibernateUtilOldVersion.getSession();
        session.beginTransaction();
        session.update(people);
        session.flush();
        session.close();
        return people;
    }

    @Override
    public void delete(People people) {
        //Session session = HibernateUtil.getSessionFactory().openSession();
        Session session = HibernateUtilOldVersion.getSession();
        session.beginTransaction();
        session.delete(people);
        session.flush();
        session.close();
    }
}
