package com.db.crud.person.dto;

import java.time.LocalDate;
import java.util.List;

import com.db.crud.person.entity.Address;
import com.db.crud.person.entity.Person;

import lombok.Getter;

@Getter
public class PersonPageableDTO {

    Long ID;
    String firstName;
    String lastName;
    LocalDate birthDate;
    String cpf;
    List<Address> addresses;

    public PersonPageableDTO(Person person){
        this.ID = person.getID();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName(); 
        this.birthDate = person.getBirthDate(); 
        this.cpf = person.getCpf(); 
        this.addresses = person.getAddress();
    }
}
