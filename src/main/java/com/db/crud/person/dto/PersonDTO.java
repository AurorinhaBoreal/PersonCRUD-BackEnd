package com.db.crud.person.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.db.crud.person.entity.Address;
// import com.db.crud.person.entity.Person.PersonBuilder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    @NotBlank(message = "Primeiro nome não ser vazio")
    private String firstName;

    @NotBlank(message = "Último nome não pode ser vazio")
    private String lastName;

    @NotBlank(message = "Informe um CPF!")
    @Size(min=10, message = "CPF inválido!")
    private String cpf;

    @NotNull(message = "Informe uma data válida!")
    private LocalDate birthDate;

    @NotEmpty(message = "Informe um endereço válido!")
    private List<Address> address;

    // Por meio do BeanUtils que copia os atributos do PersonDTO, ele permite fazermos a conversão de Entity para DTO
    // public PersonDTO(PersonBuilder person) {
    //     BeanUtils.copyProperties(person, this);
    // }
}
