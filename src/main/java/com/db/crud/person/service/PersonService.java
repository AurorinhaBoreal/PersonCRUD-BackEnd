package com.db.crud.person.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.db.crud.person.dto.mapper.PersonMapper;
import com.db.crud.person.dto.request.PersonRequest;
import com.db.crud.person.dto.response.PersonResponse;
import com.db.crud.person.entity.Person;
import com.db.crud.person.exception.DuplicateCpfException;
import com.db.crud.person.exception.ObjectNotFoundException;
import com.db.crud.person.repository.PersonRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonResponse> findAll(Pageable pageable) {
        log.info("Searching for Persons in the Database...");

        return personRepository.findAll(pageable).map(person -> {
            calcAge(person);
            return PersonMapper.personToDto(person);
        }).toList();
    }

    private boolean verifyCPF(String cpf) {
        if (personRepository.existsByCpf(cpf)) {
            throw new DuplicateCpfException("Already exists a person with this cpf!");
        }
        return false;
    }

    @Transactional
    public PersonResponse create(PersonRequest personDTO) {
        Person person = PersonMapper.dtoToPerson(personDTO);
        verifyCPF(person.getCpf());
        personRepository.save(person);
        calcAge(person);
        log.info("Successfully created Person. Person: " + person);

        return PersonMapper.personToDto(person);
    }

    @Transactional
    public PersonResponse update(PersonRequest personUpdate, String cpf) {
        Person personOriginal = findPerson(cpf);

        personOriginal.setFirstName(personUpdate.firstName());
        personOriginal.setLastName(personUpdate.lastName());
        personOriginal.setCpf(personUpdate.cpf());
        personOriginal.setBirthDate(personUpdate.birthDate());
        personOriginal.setPhotoId(personUpdate.photoId());
        personRepository.save(personOriginal);

        return PersonMapper.personToDto(personOriginal);
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
        return personRepository.findByCpf(cpf).orElseThrow(
                () -> new ObjectNotFoundException("No Person Found with this cpf " + cpf));
    }
}
