package com.db.crud.person.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePersonRequest( 
    @NotBlank(message = "First name cannot be empty!")
    String firstName,

    @NotBlank(message = "Last name cannot be empty!")
    String lastName,

    @NotNull(message = "Data must be valid!")
    LocalDate birthDate,
    
    @NotNull(message = "Must inform a photoId")
    Integer photoId) {}
