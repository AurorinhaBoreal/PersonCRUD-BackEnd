package com.db.crud.person.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.db.crud.person.dto.PersonDTO;
import com.db.crud.person.entity.Person;
import com.db.crud.person.exception.CreatePersonException;
import com.db.crud.person.exception.DeletePersonException;
import com.db.crud.person.exception.UpdatePersonException;
import com.db.crud.person.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonService {

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

    public Person create(Person person) {
        try {
            repository.save(person);
            log.info("Pessoa criada com sucesso. Pessoa: "+person); 
            return person;
        } catch (Exception e) {
            throw new CreatePersonException("Não foi possivel criar a pessoa!");
        }
    }

    public Person update(PersonDTO personUpdate, Long personID) {
        try {
            Person personOriginal = repository.findById(personID).get();

            personOriginal.setFirstName(personUpdate.getFirstName());
            personOriginal.setLastName(personUpdate.getLastName());
            personOriginal.setCpf(personUpdate.getCpf());
            personOriginal.setBirthDate(personUpdate.getBirthDate());
            repository.save(personOriginal);
            return personOriginal;
        } catch (Exception e) {
            throw new UpdatePersonException("Não foi possivel atualizar os dados de Pessoa");
        }
    }

    public void delete(Long personID) {
        try {
            repository.deleteById(personID);    
        } catch (Exception e) {
            throw new DeletePersonException("Não foi possivel deletar a Pessoa!");
        }
        
    }

    public String calcAge(Long personID) {
        Person person = repository.findById(personID).get();
        LocalDate birthDate = person.getBirthDate();
        LocalDate currentDate = LocalDate.now();


        int age = Period.between(birthDate, currentDate).getYears();
        String info = "A idade de "+person.getFirstName()+" é de "+age+" anos.";
        log.info(info);
        return info;
    }

}
