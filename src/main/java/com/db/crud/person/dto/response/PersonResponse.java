package com.db.crud.person.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.db.crud.person.entity.Address;

import lombok.Builder;

@Builder
public record PersonResponse( 
    String firstName,
    String lastName,
    LocalDate birthDate,
    Integer age,
    String cpf,
    boolean hasMainAddress,
    Integer photoId,
    List<Address> addresses
) {}