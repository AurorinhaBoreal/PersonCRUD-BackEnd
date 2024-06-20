package com.db.crud.person.dto.mapper;

import org.mapstruct.Mapper;

import com.db.crud.person.dto.request.AddressRequest;
import com.db.crud.person.dto.response.AddressResponse;
import com.db.crud.person.entity.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    static AddressResponse addressToDto(Address address) {
        return AddressResponse.builder()
            .addressIdentifier(address.getAddressIdentifier())
            .zipCode(address.getZipCode())
            .street(address.getStreet())
            .number(address.getNumber())
            .complement(address.getComplement())
            .neighborhood(address.getNeighborhood())
            .city(address.getCity())
            .uf(address.getUf())
            .country(address.getCountry())
            .build();

    }

    static Address dtoToAddress(AddressRequest addressDTO) {
        return Address.builder()
            .addressIdentifier(addressDTO.addressIdentifier())
            .zipCode(addressDTO.zipCode())
            .street(addressDTO.street())
            .number(addressDTO.number())
            .complement(addressDTO.complement())
            .neighborhood(addressDTO.neighborhood())
            .city(addressDTO.city())
            .uf(addressDTO.uf())
            .country(addressDTO.country())
            .mainAddress(addressDTO.mainAddress())
            .build();
    }
}
