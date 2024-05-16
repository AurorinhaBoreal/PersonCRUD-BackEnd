package com.db.crud.person.dto;

import java.time.LocalDate;
import java.util.List;

import com.db.crud.person.entity.Address;
import com.db.crud.person.entity.Person;

public record ResponsePersonDTO( 
    String firstName,
    String lastName,
    LocalDate birthDate,
    Integer age,
    String cpf,
    List<Address> addresses
) {
    public ResponsePersonDTO(Person person){
        this(person.getFirstName(), person.getLastName(), person.getBirthDate(), person.getAge(), person.getCpf(), person.getAddress());
    }
}