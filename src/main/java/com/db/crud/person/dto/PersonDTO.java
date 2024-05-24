package com.db.crud.person.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    @NotBlank(message = "Primeiro nome não pode ser vazio")
    private String firstName;

    @NotBlank(message = "Último nome não pode ser vazio")
    private String lastName;

    @NotBlank(message = "Informe um CPF!")
    @CPF(message = "CPF Inválido!")
    private String cpf;

    @NotNull(message = "Informe uma data válida!")
    private LocalDate birthDate;

    @Override
    public String toString() {
        return "CPF: "+this.cpf
            +" | Nome: "+this.firstName+" "+this.lastName
            +" | Data de Nascimento: "+this.birthDate+" |";
    }
    
    
}
