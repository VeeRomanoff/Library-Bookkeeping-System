package com.neil.bookkeping.repositories;

import com.neil.bookkeping.DAO.BookDAO;
import com.neil.bookkeping.entities.Book;
import com.neil.bookkeping.entities.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDataAccessService implements BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> getAllBooks() {
        String SQL = "SELECT * FROM book";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public Book getOneBook(Integer id) {
        String SQL = "SELECT * FROM book WHERE id = ?";
        return jdbcTemplate.query(
                        SQL,
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class))
                .stream()
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void addBookToDatabase(Book book) {
        String SQL = "INSERT INTO book(title, author, year_of_publishing) VALUES (?, ?, ?)";
        jdbcTemplate.update(SQL, book.getTitle(), book.getAuthor(), book.getYearOfPublishing());
    }

    @Override
    public void updateBook(Book updatedBook, Integer id) {
        String SQL = "UPDATE book SET title = ?, author = ?, year_of_publishing = ? WHERE id = ?";
        jdbcTemplate.update(SQL, updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYearOfPublishing(), id);
    }

    @Override
    public void deleteBook(Integer id) {
        String SQL = "DELETE FROM book WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    @Override
    public Optional<Person> getOwnerOfBook(Integer id) {
        String SQL = "SELECT Person.* FROM book JOIN person ON book.person_id = person.id WHERE book.id = ?";
        return jdbcTemplate.query(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    @Override
    public void assignBook(Integer id, Person person) {
        String SQL = "UPDATE book SET person_id = ? WHERE id = ?";
        jdbcTemplate.update(SQL, person.getId(), id);
    }

    @Override
    public void releaseBook(Integer id) {
        String SQL = "UPDATE book SET person_id = NULL WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }
}
