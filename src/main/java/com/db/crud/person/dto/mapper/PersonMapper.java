package com.db.crud.person.dto.mapper;

import org.mapstruct.Mapper;

import com.db.crud.person.dto.request.PersonRequest;
import com.db.crud.person.dto.response.PersonResponse;
import com.db.crud.person.entity.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    static PersonResponse personToDto(Person person) {
        return PersonResponse.builder()
            .firstName(person.getFirstName())
            .lastName(person.getLastName())
            .birthDate(person.getBirthDate())
            .age(person.getAge())
            .cpf(person.getCpf())
            .hasMainAddress(person.isHasMainAddress())
            .addresses(person.getAddress())
            .photoId(person.getPhotoId())
            .build();
    }

    static Person dtoToPerson(PersonRequest personDTO) {
        return Person.builder()
            .firstName(personDTO.firstName())
            .lastName(personDTO.lastName())
            .cpf(personDTO.cpf())
            .birthDate(personDTO.birthDate())
            .photoId(personDTO.photoId())
            .build();
    }
}
