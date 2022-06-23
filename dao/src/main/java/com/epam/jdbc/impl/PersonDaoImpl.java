package com.epam.jdbc.impl;

import com.epam.DefaultDao;
import com.epam.jdbc.DatabaseConnection;
import com.epam.jdbc.entity.Person;
import com.epam.springdata.entity.Gender;
import lombok.Setter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements DefaultDao<Person> {

    @Setter
    private DatabaseConnection databaseConnection;

    @Override
    public Person create(Person person) {
        String query = "insert into Person (firstName, lastName, birthday, email, password, gender)" +
                " values (?, ?, ?, ?, ?, ?);";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setDate(3, Date.valueOf(person.getBirthday()));
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.setString(5, person.getPassword());
            preparedStatement.setString(6, person.getGender().name());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                person.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            //TODO better use log4j
            System.out.println("Something went wrong: " + e.getMessage());
        }

        return person;
    }

    @Override
    public Person update(Person person) {
        String query = "Update person set firstName = ?, lastName = ?, birthday = ?, " +
                "email =?, password = ?, gender = ? where id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setDate(3, Date.valueOf(person.getBirthday()));
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.setString(5, person.getPassword());
            preparedStatement.setString(6, person.getGender().name());
            preparedStatement.setInt(7, person.getId());
            int row = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return person;
    }

    // TODO this method may be return null
    @Override
    public Person getById(Integer id) {
        String query = "select * from person where id = ?";
        Person person = null;
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            person = parsePerson(resultSet);

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return person;
    }

    // TODO this method may be produce empty List
    @Override
    public List<Person> getAll() {
        String query = "select * from person;";
        List<Person> persons = new ArrayList<>();

        try (Connection connection = databaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Person person = parsePerson(resultSet);
                persons.add(person);
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
        return persons;
    }


    @Override
    public void delete(Person person) {
        String query = "delete from person where id = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, person.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
    }

    private Person parsePerson(ResultSet rs) throws SQLException {
        return new Person(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getDate(4).toLocalDate(),
                rs.getString(5),
                rs.getString(6),
                Gender.valueOf(rs.getString(7)));
    }
}
