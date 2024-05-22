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

    static Address dtoToAddress(AddressRequest addressDTO) {
        return Address.builder()
            .zipCode(addressDTO.zipCode())
            .street(addressDTO.street())
            .number(addressDTO.number())
            .neighborhood(addressDTO.neighborhood())
            .city(addressDTO.city())
            .uf(addressDTO.uf())
            .country(addressDTO.country())
            .mainAddress(addressDTO.mainAddress())
            .build();
    }
}
