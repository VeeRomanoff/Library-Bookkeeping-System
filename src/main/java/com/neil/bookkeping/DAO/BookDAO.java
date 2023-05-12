package com.neil.bookkeping.DAO;

import com.neil.bookkeping.entities.Book;
import com.neil.bookkeping.entities.Person;

import java.util.List;
import java.util.Optional;

public interface BookDAO {
    List<Book> getAllBooks();
    Book getOneBook(Integer id);
    void addBookToDatabase(Book book);
    void updateBook(Book book, Integer id);
    void deleteBook(Integer id);
    Optional<Person> getOwnerOfBook(Integer id);
    void assignBook(Integer id, Person person);
    void releaseBook(Integer id);
}
