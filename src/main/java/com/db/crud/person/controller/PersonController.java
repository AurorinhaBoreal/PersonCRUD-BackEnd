package com.db.crud.person.controller;

import org.springframework.web.bind.annotation.RestController;

import com.db.crud.person.dto.PersonDTO;
import com.db.crud.person.entity.Person;
import com.db.crud.person.service.PersonService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/person")
@Validated
@Slf4j
public class PersonController {

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

    @PatchMapping("/update/{personID}")
    public void updateUser(@RequestBody @Valid PersonDTO person, @PathVariable Long personID) {
        log.info("Atualizando Pessoa: "+person);
        service.update(person, personID);
    }

    @DeleteMapping("/delete/{personID}")
    public ResponseEntity<String> deleteUser(@PathVariable Long personID) {
        service.delete(personID);
        return ResponseEntity.status(HttpStatus.OK).body("A pessoa com ID "+personID+" foi deletada com sucesso!");
    }

    @GetMapping("/age/{personID}")
    public ResponseEntity<String> getAge(@PathVariable Long personID) {
        var info = service.calcAge(personID);
        return ResponseEntity.status(HttpStatus.OK).body(info);
    }
    
}