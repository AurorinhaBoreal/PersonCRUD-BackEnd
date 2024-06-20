package com.db.crud.person.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import org.hibernate.validator.constraints.br.CPF;


public record PersonRequest( 
    @NotBlank(message = "First name cannot be empty!")
    String firstName,

    @NotBlank(message = "Last name cannot be empty!")
    String lastName,

    @NotBlank(message = "CPF cannot be empty!")
    @CPF(message = "Invalid CPF!")
    String cpf,

    @NotNull(message = "Data must be valid!")
    LocalDate birthDate,
    
    @NotNull(message = "Must inform a photoId")
    Integer photoId) {}
