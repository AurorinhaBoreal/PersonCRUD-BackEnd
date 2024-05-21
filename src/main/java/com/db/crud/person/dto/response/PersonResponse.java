package com.db.crud.person.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.db.crud.person.entity.Address;
import com.db.crud.person.entity.Person;

public record PersonResponse( 
    String firstName,
    String lastName,
    LocalDate birthDate,
    Integer age,
    String cpf,
    boolean hasMainAddress,
    List<Address> addresses
) {
    public PersonResponse(Person person){
        this(person.getFirstName(), person.getLastName(), person.getBirthDate(), person.getAge(), person.getCpf(), person.isHasMainAddress(), person.getAddress());
    }
}