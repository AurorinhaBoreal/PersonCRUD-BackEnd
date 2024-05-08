package com.db.crud.person.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import com.db.crud.person.dto.PersonDTO;
import com.db.crud.person.dto.PersonPageableDTO;
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

    public Page<Object> findAll(Pageable pageable) {
        try {
            log.info("Pessoas Registradas:");
            return repository.findAll(pageable).map(PersonPageableDTO::new);
            
        } catch (Exception e) {
            throw new UnsupportedOperationException("Erro ao mostrar páginação!");
        }
    }

    private boolean verifyCPF(String cpf) {
        if (repository.existsByCpf(cpf) == true) {
            throw new CreatePersonException("Já existe um usuario com esse CPF!");
        }
        return false;
    }

    public Person create(Person person) {
        try {
            verifyCPF(person.getCpf());
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

    public Person delete(Long personID) {
        try {
            Person person = repository.findById(personID).get();
            repository.delete(person);
            return person;
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
