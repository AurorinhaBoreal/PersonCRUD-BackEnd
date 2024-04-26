package com.db.crud.person.dto;

import jakarta.validation.constraints.NotBlank;
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
public class AddressDTO {
    
    @NotBlank(message = "Informe um código postal (CEP)")
    @Size(min = 7, max = 12, message = "Informe um código postal válido")
    private String zipCode;

    @NotBlank(message = "Informe uma rua!")
    private String street;

    @NotBlank(message = "Informe um número!")
    private String number;

    @NotBlank(message = "Informe um logradouro!")
    private String neighborhood;

    @NotBlank(message = "Informe uma cidade!")
    private String city;

    @NotBlank(message = "Informe uma sigla para o estado!")
    @Size(min = 2, max = 2, message = "Sigla inválida!")
    private String UF;

    @NotBlank(message = "Informe um país!")
    private String country;

    // public AddressDTO(AdressBuilder address) {
    //     BeanUtils.copyProperties(address, this);
    // }
}
