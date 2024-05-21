package com.db.crud.person.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.db.crud.person.dto.request.AddressRequest;
import com.db.crud.person.dto.response.AddressResponse;
import com.db.crud.person.entity.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressResponse addressToDto(Address address);

    Address dtoToAddress(AddressRequest address);
}
