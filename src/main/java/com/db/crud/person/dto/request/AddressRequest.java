package com.db.crud.person.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record AddressRequest(
    @NotBlank(message = "Informe um código postal (CEP)")
    @Size(min = 7, max = 12, message = "Informe um código postal válido")
    String zipCode,

    @NotBlank(message = "Informe uma rua!")
    String street,

    @NotBlank(message = "Informe um número!")
    String number,

    String complement,

    @NotBlank(message = "Informe um logradouro!")
    String neighborhood,

    @NotBlank(message = "Informe uma cidade!")
    String city,

    @NotBlank(message = "Informe uma sigla para o estado!")
    @Size(min = 2, max = 2, message = "Sigla inválida!")
    String uf,

    @NotBlank(message = "Informe um país!")
    String country,

    @NotNull(message = "Especifique se é o endereço principal ou não")
    boolean mainAddress
) {
}
