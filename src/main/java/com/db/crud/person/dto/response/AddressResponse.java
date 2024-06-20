package com.db.crud.person.dto.response;

import lombok.Builder;

@Builder
public record AddressResponse(
    Long addressIdentifier,
    String zipCode,
    String street,
    String number,
    String complement,
    String neighborhood,
    String city,
    String uf,
    String country,
    boolean mainAddress
) {}