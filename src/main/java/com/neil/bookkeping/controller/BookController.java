package com.neil.bookkeping.controller;

import com.neil.bookkeping.entities.Book;
import com.neil.bookkeping.entities.Person;
import com.neil.bookkeping.service.BookService;
import com.neil.bookkeping.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PersonService personService;

    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/showAllBooks";
    }

    @GetMapping("/{id}")
    public String showOneBook(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("book", bookService.getOneBook(id));

        Optional<Person> ownerOfBook = bookService.getOwnerOfBook(id);
        if (ownerOfBook.isPresent()) {
            model.addAttribute("owner", ownerOfBook.get());
        } else {
            model.addAttribute("people", personService.getAllPeople());
        }
        return "books/showOneBook";
    }

    @GetMapping("/add")
    public String showAddingForm(@ModelAttribute("book") Book Book) {
        return "books/addAnotherBookForm";
    }

    @PostMapping
    public String addAnotherBook(@ModelAttribute("book") @Valid Book book,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "books/addAnotherBookForm";
        }

        bookService.addBookToDatabase(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") Integer id, @ModelAttribute("person") Person selectedPerson) {
        bookService.assign(id, selectedPerson);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") Integer id) {
        bookService.releaseBook(id);
        return "redirect:/books/" + id;
    }

    @GetMapping("/{id}/edit")
    public String showEditPage(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("book", bookService.getOneBook(id));
        return "books/showEditPage";
    }

    @PatchMapping("/{id}")
    public String editBook(
            @PathVariable("id") Integer id,
            @ModelAttribute("book") @Valid Book updatedBook,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "books/showEditPage";
        }

        bookService.updateBook(id, updatedBook);
        return "redirect:/books/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
