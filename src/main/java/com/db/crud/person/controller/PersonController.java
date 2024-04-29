package com.db.crud.person.controller;

import org.springframework.web.bind.annotation.RestController;

import com.db.crud.person.dto.PersonDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;

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
    
    @GetMapping("/list")
    public void listPersons() {

    }

    @PostMapping("/create")
    public void createUser(@RequestBody @Valid PersonDTO person) {
        logger.log(Level.INFO, "O Corpo da pessoa: \n"+person);
    }
}