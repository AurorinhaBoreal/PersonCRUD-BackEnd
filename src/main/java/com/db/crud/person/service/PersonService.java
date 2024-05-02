package com.db.crud.person.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.crud.person.controller.PersonController;
import com.db.crud.person.entity.Person;
import com.db.crud.person.exception.CreatePersonException;
import com.db.crud.person.repository.PersonRepository;

@Service
public class PersonService {
    
    private Logger logger = Logger.getLogger(PersonController.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> list() {
        return repository.findAll();
    }

    public void verifyCPF(String cpf) {
        if (repository.existsByCpf(cpf) == true) {
            throw new CreatePersonException("Já existe um usuario com esse CPF!");
        }
    }

    public void create(Person person) {
        try {
            repository.save(person);
            logger.log(Level.INFO, "Pessoa criada com sucesso. Pessoa: "+person);
        } catch (Exception e) {
            throw new CreatePersonException("Não foi possivel criar a pessoa!");
        }
    }

}
