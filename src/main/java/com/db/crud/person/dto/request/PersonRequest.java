package com.db.crud.person.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import org.hibernate.validator.constraints.br.CPF;


public record PersonRequest( 
    @NotBlank(message = "Primeiro nome não pode ser vazio")
    String firstName,

    @NotBlank(message = "Último nome não pode ser vazio")
    String lastName,

    @NotBlank(message = "Informe um CPF!")
    @CPF(message = "CPF Inválido!")
    String cpf,

    @NotNull(message = "Informe uma data válida!")
    LocalDate birthDate) {}
