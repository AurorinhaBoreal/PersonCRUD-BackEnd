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
import lombok.extern.slf4j.Slf4j;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@Slf4j
public class AddressController {    
    Logger logger = Logger.getLogger(AddressController.class.getName());

    @Autowired
    private AddressService service;
    
    @GetMapping("/list")
    public ResponseEntity<List<Address>> listAddress() {
        var body = service.list();

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    // TODO: Fix insertion of Address in Database - Assign Address to Person
    @PostMapping("/{personID}/assign")
    public ResponseEntity<Address> createAddress(@RequestBody @Valid AddressDTO address, @PathVariable Long personID) {
        // System.out.println("O DTO do Address: "+address);

        System.out.println(address.isMainAddress());

        var body = service.create(address, personID);

        logger.log(Level.INFO, "O Corpo do endereço: \n"+address);

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
        
    }
    
}
