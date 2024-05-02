package com.db.crud.person.controller;

import org.springframework.web.bind.annotation.RestController;

import com.db.crud.person.dto.AddressDTO;
import com.db.crud.person.entity.Address;
import com.db.crud.person.entity.Person;
import com.db.crud.person.repository.AddressRepository;
import com.db.crud.person.repository.PersonRepository;
import com.db.crud.person.service.AddressService;
import com.db.crud.person.service.PersonService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {    
    Logger logger = Logger.getLogger(AddressController.class.getName());

    @Autowired // Injeção de Dependências - Injetando a Person Repository na Controller
    private AddressRepository repoA;

    @Autowired
    private AddressService servA;

    @Autowired
    private PersonRepository repoP;
    
    @GetMapping("/list")
    public List<Address> listAddress() {
        return servA.list();
    }

    // TODO: Fix insertion of Address in Database - Assign Address to Person
    // @PostMapping("/{personID}/assign")
    // public void createAddress(@RequestBody @Valid AddressDTO address, @PathVariable Long personID) {
    //     Person person = repoP.findById(personID).get();
    //     repoA.save(new Address(address));
    //     logger.log(Level.INFO, "O Corpo do endereço: \n"+address);
    //     person.
    // }
    
}
