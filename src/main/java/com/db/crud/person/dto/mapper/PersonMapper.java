package com.db.crud.person.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.db.crud.person.dto.request.PersonRequest;
import com.db.crud.person.dto.response.PersonResponse;
import com.db.crud.person.entity.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonResponse personToDto(Person person);

    static Person dtoToPerson(PersonRequest personDTO) {
        return Person.builder()
            .firstName(personDTO.firstName())
            .lastName(personDTO.lastName())
            .cpf(personDTO.cpf())
            .birthDate(personDTO.birthDate())
            .build();
    }
}
