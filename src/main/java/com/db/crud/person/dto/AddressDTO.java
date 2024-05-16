package com.db.crud.person.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: FIX CODE BECAUSE OF RECORD!!
@Data
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

    private String complement;

    @NotBlank(message = "Informe um logradouro!")
    private String neighborhood;

    @NotBlank(message = "Informe uma cidade!")
    private String city;

    @NotBlank(message = "Informe uma sigla para o estado!")
    @Size(min = 2, max = 2, message = "Sigla inválida!")
    private String uf;

    @NotBlank(message = "Informe um país!")
    private String country;

    @NotNull(message = "Especifique se é o endereço principal ou não")
    private boolean mainAddress;

    public String toString() {
        return "Código Postal: "+this.zipCode
            +" | Rua: "+this.street
            +" | Numero: "+this.number
            +" | Complemento: "+this.complement
            +" | Bairro: "+this.neighborhood
            +" | Cidade: "+this.city
            +" | uf: "+this.uf
            +" | País: "+this.country
            +" | Principal: "+this.mainAddress;
    }
}
