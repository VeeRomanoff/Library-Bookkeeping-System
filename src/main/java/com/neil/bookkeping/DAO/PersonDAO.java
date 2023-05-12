package com.neil.bookkeping.DAO;

import com.neil.bookkeping.entities.Book;
import com.neil.bookkeping.entities.Person;
import java.util.List;

public interface PersonDAO {
    List<Person> getAllPeople();
    Person getOnePerson(Integer id);
    void addPersonToDatabase(Person book);
    void updatePerson(Person person, Integer id);
    void deletePerson(Integer id);
    List<Book> getBooksByPersonId(Integer id);
}