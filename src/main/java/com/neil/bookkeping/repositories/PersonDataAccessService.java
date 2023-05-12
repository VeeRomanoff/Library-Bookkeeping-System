package com.neil.bookkeping.repositories;

import com.neil.bookkeping.DAO.PersonDAO;
import com.neil.bookkeping.entities.Book;
import com.neil.bookkeping.entities.Person;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDataAccessService implements PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> getAllPeople() {
        String SQL = "SELECT * FROM person";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    public Person getOnePerson(Integer id) {
        String SQL = "SELECT * FROM person WHERE id = ?";
        return jdbcTemplate.query(
                        SQL,
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public void addPersonToDatabase(Person person) {
        String SQL = "INSERT INTO person(full_name, year_of_birth) VALUES (?, ?)";
        jdbcTemplate.update(SQL, person.getFullName(), person.getYearOfBirth());
    }

    @Override
    public void updatePerson(Person updatedPerson, Integer id) {
        String SQL = "UPDATE person SET full_name = ?, year_of_birth = ? WHERE id = ?";
        jdbcTemplate.update(SQL, updatedPerson.getFullName(), updatedPerson.getYearOfBirth(), id);
    }

    @Override
    public List<Book> getBooksByPersonId(Integer id) {
        String SQL = ("SELECT * FROM book WHERE person_id = ?");
        return jdbcTemplate.query(
                SQL,
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public void deletePerson(Integer id) {
        String SQL = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }
}
