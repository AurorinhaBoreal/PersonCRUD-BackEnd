package com.db.crud.person.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import com.db.crud.person.dto.RequestPersonDTO;
import com.db.crud.person.dto.ResponsePersonDTO;
import com.db.crud.person.entity.Person;
import com.db.crud.person.exception.DuplicateCpfException;
import com.db.crud.person.exception.PersonNotFoundException;
import com.db.crud.person.mapper.PersonMapper;
import com.db.crud.person.repository.PersonRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    // TODO: Verify if this method is really necessary
    // public List<Person> list() {
    //     return personRepository.findAll();
    // }

    public Page<Object> findAll(Pageable pageable) {
        log.info("Pessoas Registradas:");
        
        return personRepository.findAll(pageable).map(person -> {
            calcAge(person);
            return new ResponsePersonDTO(person);
        });
    }

    private boolean verifyCPF(String cpf) {
        if (personRepository.existsByCpf(cpf)) {
            throw new DuplicateCpfException("Already exists a person with this cpf!");
        }
        return false;
    }

    @Transactional
    public ResponsePersonDTO create(Person person) {
            verifyCPF(person.getCpf());

            personRepository.save(person);
            log.info("Successfully created Person. Person: "+person);

            ResponsePersonDTO responsePerson = PersonMapper.INSTANCE.personToDto(person);
            return responsePerson;
    }

    @Transactional
    public ResponsePersonDTO update(RequestPersonDTO personUpdate, String cpf) {
        Person personOriginal = findPerson(cpf);

        personOriginal.setFirstName(personUpdate.firstName());
        personOriginal.setLastName(personUpdate.lastName());
        personOriginal.setCpf(personUpdate.cpf());
        personOriginal.setBirthDate(personUpdate.birthDate());
        personRepository.save(personOriginal);

        ResponsePersonDTO responsePerson = PersonMapper.INSTANCE.personToDto(personOriginal);
        return responsePerson;
    }

    public Person delete(String cpf) {
        Person person = findPerson(cpf);

        personRepository.delete(person);
        return person;
    }

    public Integer calcAge(Person person) {
        LocalDate birthDate = person.getBirthDate();
        LocalDate currentDate = LocalDate.now();
        
        int age = Period.between(birthDate, currentDate).getYears();
        person.setAge(age);
        return age;
    }

    public Person findPerson(String cpf) {
        Person person = personRepository.findByCpf(cpf).orElseThrow(
            () -> new PersonNotFoundException("No Person Found with this cpf "+cpf));
        return person;
    }
}
