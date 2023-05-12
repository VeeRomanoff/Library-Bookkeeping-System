package com.neil.bookkeping.service;

import com.neil.bookkeping.DAO.BookDAO;
import com.neil.bookkeping.entities.Book;
import com.neil.bookkeping.entities.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookDAO bookDAO;

    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public Book getOneBook(Integer id) {
        return bookDAO.getOneBook(id);
    }

    public void addBookToDatabase(Book book) {
        bookDAO.addBookToDatabase(book);
    }

    public Optional<Person> getOwnerOfBook(Integer id) {
        return bookDAO.getOwnerOfBook(id);
    }

    public void assign(Integer id, Person person) {
        bookDAO.assignBook(id, person);
    }

    public void releaseBook(Integer id) {
        bookDAO.releaseBook(id);
    }

    public void updateBook(Integer id, Book updatedBook) {
        bookDAO.updateBook(updatedBook, id);
    }

    public void deleteBook(Integer id) {
        bookDAO.deleteBook(id);
    }
}
