package com.db.crud.person.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import lombok.Builder;

// TODO: FIX CODE BECAUSE OF RECORD!!
@Builder
public record PersonDTO( 
    @NotBlank(message = "Primeiro nome não pode ser vazio")
    String firstName,

    @NotBlank(message = "Último nome não pode ser vazio")
    String lastName,

    @NotBlank(message = "Informe um CPF!")
    @CPF(message = "CPF Inválido!")
    String cpf,

    @NotNull(message = "Informe uma data válida!")
    LocalDate birthDate) {

   ;

    @Override
    public String toString() {
        return "CPF: "+this.cpf
            +" | Nome: "+this.firstName+" "+this.lastName
            +" | Data de Nascimento: "+this.birthDate+" |";
    }
    
    
}
