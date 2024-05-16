package com.db.crud.person.controller;

import org.springframework.web.bind.annotation.RestController;

import com.db.crud.person.dto.PersonDTO;
import com.db.crud.person.entity.Person;
import com.db.crud.person.service.PersonService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;




@RestController
@RequestMapping("/person")
@Validated
@Slf4j
public class PersonController {

    @Autowired
    private PersonService personService;
    
    @GetMapping("/list")
    public List<Person> listPersons() {
        return personService.list();
    }

    @GetMapping("/pageable")
    public Page<Object> listPageable(@PageableDefault(size=3, sort = {"firstName"}) Pageable pageable) {
        return personService.findAll(pageable);
    }
    

    @PostMapping("/create")
    public ResponseEntity<Person> createUser(@RequestBody @Valid PersonDTO person) {
        var info = personService.create(new Person(person));
        return ResponseEntity.status(HttpStatus.CREATED).body(info);
    }

    @PatchMapping("/update/{personID}")
    public ResponseEntity<Person> updateUser(@RequestBody @Valid PersonDTO person, @PathVariable Long personID) {
        log.info("Atualizando Pessoa: "+person);
        var info = personService.update(person, personID);
        return ResponseEntity.status(HttpStatus.OK).body(info);
    }

    @DeleteMapping("/delete/{personID}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long personID) {
        personService.delete(personID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/age/{personID}")
    public ResponseEntity<String> getAge(@PathVariable Long personID) {
        var info = personService.calcAge(personID);
        return ResponseEntity.status(HttpStatus.OK).body(info);
    }
    
}