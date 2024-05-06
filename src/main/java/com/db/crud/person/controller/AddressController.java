package com.db.crud.person.controller;

import org.springframework.web.bind.annotation.RestController;

import com.db.crud.person.dto.AddressDTO;
import com.db.crud.person.entity.Address;
import com.db.crud.person.repository.AddressRepository;
import com.db.crud.person.repository.PersonRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/address")
public class AddressController {    
    Logger logger = Logger.getLogger(AddressController.class.getName());

    @Autowired // Injeção de Dependências - Injetando a Person Repository na Controller
    private AddressRepository repository;
    
    @GetMapping("/list")
    public void listAddress() {

    }

    @PostMapping("/create")
    public void createUser(@RequestBody @Valid AddressDTO address) {
        repository.save(new Address(address));
        logger.log(Level.INFO, "O Corpo do endereço: \n"+address);
    }
    
}
