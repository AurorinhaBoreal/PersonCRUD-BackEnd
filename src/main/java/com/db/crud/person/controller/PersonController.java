package com.db.crud.person.controller;

import org.springframework.web.bind.annotation.RestController;

import com.db.crud.person.dto.PersonDTO;
import com.db.crud.person.entity.Person;
import com.db.crud.person.repository.PersonRepository;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping("/person")
public class PersonController {
    Logger logger = Logger.getLogger(PersonController.class.getName());

    @Autowired // Injeção de Dependências - Injetando a Person Repository na Controller
    private PersonRepository repository;
    
    @GetMapping("/list")
    public void listPersons() {

    }

    @PostMapping("/create")
    public void createUser(@RequestBody @Valid PersonDTO person) {
        repository.save(new Person(person));
        logger.log(Level.INFO, "O Corpo da pessoa: \n"+person);
    }
}