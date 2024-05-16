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
import com.db.crud.person.exception.CreatePersonException;
import com.db.crud.person.exception.DeletePersonException;
import com.db.crud.person.exception.GetInfoException;
import com.db.crud.person.exception.UpdatePersonException;
import com.db.crud.person.mapper.PersonMapper;
import com.db.crud.person.repository.PersonRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonService {

    @Autowired
    PersonRepository repository;

    public List<Person> list() {
        return repository.findAll();
    }

    // TODO: FIX AFTER MAPPER
    public Page<Object> findAll(Pageable pageable) {
        try {
            log.info("Pessoas Registradas:");
            
            return repository.findAll(pageable).map(person -> {
                calcAge(person);
                return new ResponsePersonDTO(person);
            });
                
        } catch (Exception e) {
            throw new GetInfoException("Erro ao mostrar páginação!");
        }
    }

    private boolean verifyCPF(String cpf) {
        if (repository.existsByCpf(cpf) == true) {
            throw new CreatePersonException("Já existe um usuario com esse CPF!");
        }
        return false;
    }

    @Transactional
    public ResponsePersonDTO create(Person person) {
        try {
            verifyCPF(person.getCpf());
            repository.save(person);
            log.info("Pessoa criada com sucesso. Pessoa: "+person);
            ResponsePersonDTO responsePerson = PersonMapper.INSTANCE.personToDto(person);
            return responsePerson;
        } catch (Exception e) {
            throw new CreatePersonException("Não foi possivel criar a pessoa!");
        }
    }

    @Transactional
    public ResponsePersonDTO update(RequestPersonDTO personUpdate, Long personId) {
        try {
            Person personOriginal = repository.findById(personId).get();

            personOriginal.setFirstName(personUpdate.firstName());
            personOriginal.setLastName(personUpdate.lastName());
            personOriginal.setCpf(personUpdate.cpf());
            personOriginal.setBirthDate(personUpdate.birthDate());
            repository.save(personOriginal);

            ResponsePersonDTO responsePerson = PersonMapper.INSTANCE.personToDto(personOriginal);
            return responsePerson;
        } catch (Exception e) {
            throw new UpdatePersonException("Não foi possivel atualizar os dados de Pessoa");
        }
    }

    public Person delete(Long personId) {
        try {
            Person person = repository.findById(personId).get();
            repository.delete(person);
            return person;
        } catch (Exception e) {
            throw new DeletePersonException("Não foi possivel deletar a Pessoa!");
        }
    }

    public Integer calcAge(Person person) {
        try {
            
            LocalDate birthDate = person.getBirthDate();
            LocalDate currentDate = LocalDate.now();
            
            int age = Period.between(birthDate, currentDate).getYears();
            person.setAge(age);
            return age;

        } catch (Exception e) {
            throw new GetInfoException("Não foi possivel calcular a idade.");
        }
    }

}
