package com.db.crud.person.controller;

import org.springframework.web.bind.annotation.RestController;

import com.db.crud.person.dto.request.PersonRequest;
import com.db.crud.person.dto.response.PersonResponse;
import com.db.crud.person.service.PersonService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/person")
@Validated
@Slf4j
@CrossOrigin(origins = "http://localhost:5173/")
public class PersonController {

    @Autowired
    private PersonService personService;
    
    @GetMapping
    public List<PersonResponse> listPageable(@PageableDefault(sort = {"firstName"}) Pageable pageable) {
        return personService.findAll(pageable);
    }

    @PostMapping("/create")
    public ResponseEntity<PersonResponse> createUser(@RequestBody @Valid PersonRequest personDTO) {
        var body = personService.create(personDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PatchMapping("/update/{personCpf}")
    public ResponseEntity<PersonResponse> updateUser(@RequestBody @Valid PersonRequest person, @PathVariable("personCpf") String personCpf) {
        log.info("Updating Person: "+person);
        var body = personService.update(person, personCpf);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @DeleteMapping("/delete/{personCpf}")
    public ResponseEntity<Void> deleteUser(@PathVariable("personCpf") String personCpf) {
        personService.delete(personCpf);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}