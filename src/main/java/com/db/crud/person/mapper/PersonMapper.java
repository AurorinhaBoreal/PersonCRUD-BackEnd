package com.db.crud.person.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.db.crud.person.dto.RequestPersonDTO;
import com.db.crud.person.dto.ResponsePersonDTO;
import com.db.crud.person.entity.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {
 
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    // @Mapping(target = "age", ignore = true)
    ResponsePersonDTO personToDto(Person person);

    Person dtoToPerson(RequestPersonDTO personDTO);
}
