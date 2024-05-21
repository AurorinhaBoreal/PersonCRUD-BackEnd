package com.db.crud.person.dto.response;

import com.db.crud.person.entity.Address;

public record AddressResponse(
    String zipCode,
    String street,
    String number,
    String neighborhood,
    String city,
    String uf,
    String country,
    boolean mainAddress
) {
    public AddressResponse(Address address){
        this(address.getZipCode(), address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getCity(), address.getUf(), address.getCountry(), address.isMainAddress());
    }
}