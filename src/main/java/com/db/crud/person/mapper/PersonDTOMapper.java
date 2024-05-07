// package com.db.crud.person.mapper;

// import org.mapstruct.Mapping;
// import org.mapstruct.factory.Mappers;

// import com.db.crud.person.dto.PersonDTO;
// import com.db.crud.person.entity.Person;

// public interface PersonDTOMapper {

//     // Cria uma instância do Mapper
//     PersonDTOMapper INSTANCE = Mappers.getMapper(PersonDTOMapper.class);

//     // Converte de Entidade para DTO
//     @Mapping(target = "id", ignore = true)
//     PersonDTO EntityToDTO(Person person);

//     // Converte de DTO para Entidade
//     Person DTOToEntity(PersonDTO personDTO);
// }
