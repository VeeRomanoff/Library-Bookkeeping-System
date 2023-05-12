package com.neil.bookkeping.service;

import com.neil.bookkeping.DAO.PersonDAO;
import com.neil.bookkeping.entities.Book;
import com.neil.bookkeping.entities.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonDAO personDAO;

    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public List<Person> getAllPeople() {
        return personDAO.getAllPeople();
    }

    public Person getOnePerson(Integer id) {
        return personDAO.getOnePerson(id);
    }

    public List<Book> getBooksPersonId(int id) {
        return personDAO.getBooksByPersonId(id);
    }

    public void saveNewPersonToDatabase(Person person) {
        personDAO.addPersonToDatabase(person);
    }

    public void updatePerson(Integer id, Person updatedPerson) {
        personDAO.updatePerson(updatedPerson, id);
    }

    public void deletePerson(Integer id) {
        personDAO.deletePerson(id);
    }
}
