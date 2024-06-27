package com.db.crud.person.dto.request;

import jakarta.validation.constraints.NotBlank;


public record AuthenticationRequest( 
    @NotBlank(message = "Email field cannot be empty!")
    String email,

    @NotBlank(message = "Password cannot be empty!")
    String password) {}