package com.db.crud.person.controller;

import org.springframework.web.bind.annotation.RestController;

import com.db.crud.person.dto.PersonDTO;
import com.db.crud.person.entity.Person;
import com.db.crud.person.exception.CreatePersonException;
import com.db.crud.person.service.PersonService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;


@RestController
@RequestMapping("/person")
@Validated
public class PersonController {
    private Logger logger = Logger.getLogger(PersonController.class.getName());

    @Autowired // Injeção de Dependências - Injetando a Person Repository na Controller
    private PersonService service;
    
    @GetMapping("/list")
    public List<Person> listPersons() {
        return service.list();
    }

    @PostMapping("/create")
    public void createUser(@RequestBody @Valid PersonDTO person) {
        service.verifyCPF(person.getCpf());
        service.create(new Person(person));
    }
}