package com.db.crud.person.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    @NotBlank(message = "Primeiro nome não ser vazio")
    @JsonProperty(value = "firstName")
    private String firstName;

    @NotBlank(message = "Último nome não pode ser vazio")
    @JsonProperty(value = "lastName")
    private String lastName;

    @NotBlank(message = "Informe um CPF!")
    @Size(min=10, message = "CPF inválido!")
    @JsonProperty(value = "cpf")
    private String cpf;

    @NotNull(message = "Informe uma data válida!")
    @JsonProperty(value = "birthDate")
    private LocalDate birthDate;

    @NotEmpty(message = "Informe um endereço válido!")
    @JsonProperty(value = "address")
    private List<AddressDTO> address;

    @Override
    public String toString() {
        return "CPF: "+this.cpf+" | Nome: "+this.firstName+" "+this.lastName+" | Data de Nascimento: "+this.birthDate+" | Endereço: "+this.address.toString();
    }

}
