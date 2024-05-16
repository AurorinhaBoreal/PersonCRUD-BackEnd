package com.db.crud.person.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.db.crud.person.dto.RequestAddressDTO;
import com.db.crud.person.dto.ResponseAddressDTO;
import com.db.crud.person.entity.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    ResponseAddressDTO addressToDto(Address address);

    Address dtoToAddress(RequestAddressDTO address);
}
