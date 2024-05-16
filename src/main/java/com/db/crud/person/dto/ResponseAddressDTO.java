package com.db.crud.person.dto;

import com.db.crud.person.entity.Address;

public record ResponseAddressDTO(
    String zipCode,
    String street,
    String number,
    String neighborhood,
    String city,
    String uf,
    String country,
    boolean mainAddress
) {
    public ResponseAddressDTO(Address address){
        this(address.getZipCode(), address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getCity(), address.getUf(), address.getCountry(), address.isMainAddress());
    }
}