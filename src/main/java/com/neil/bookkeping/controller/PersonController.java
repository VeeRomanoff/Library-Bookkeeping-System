package com.neil.bookkeping.controller;

import com.neil.bookkeping.entities.Person;
import com.neil.bookkeping.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String getAllPeople(Model model) {
        model.addAttribute("people", personService.getAllPeople());
        return "person/showAllPeople";
    }

    @GetMapping("/{id}")
    public String showOnePerson(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("person", personService.getOnePerson(id));
        model.addAttribute("books", personService.getBooksPersonId(id));
        return "person/showOnePerson";
    }

    @GetMapping("/add")
    public String showAddingForm(@ModelAttribute("person") Person person) {
        return "person/addAnotherPersonForm";
    }

    @PostMapping
    public String addAnotherPerson(@ModelAttribute("person") @Valid Person person,
                                   BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "person/addAnotherPersonForm";
        }

        personService.saveNewPersonToDatabase(person);
        return "redirect:/people";
    }

    @PatchMapping("/{id}")
    public String editPerson(@PathVariable("id") Integer id,
                             @ModelAttribute("person") @Valid Person updatedPerson,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "person/showEditPage";
        }

        personService.updatePerson(id, updatedPerson);
        return "redirect:/people/" + id;
    }

    @GetMapping("/{id}/edit")
    public String showEditPage(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("person", personService.getOnePerson(id));
        return "person/showEditPage";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") Integer id) {
        personService.deletePerson(id);
        return "redirect:/people";
    }
}
