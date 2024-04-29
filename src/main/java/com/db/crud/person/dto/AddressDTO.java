package com.db.crud.person.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    
    @NotNull(message = "O ID da pessoa nao pode ser nulo!")
    @JsonProperty(value = "personID")
    private Integer personID;

    @NotBlank(message = "Informe um código postal (CEP)")
    @Size(min = 7, max = 12, message = "Informe um código postal válido")
    @JsonProperty(value = "zipCode")
    private String zipCode;

    @NotBlank(message = "Informe uma rua!")
    @JsonProperty(value = "street")
    private String street;

    @NotBlank(message = "Informe um número!")
    @JsonProperty(value = "number")
    private String number;

    @JsonProperty(value = "complement")
    private String complement;

    @NotBlank(message = "Informe um logradouro!")
    @JsonProperty(value = "neighborhood")
    private String neighborhood;

    @NotBlank(message = "Informe uma cidade!")
    @JsonProperty(value = "city")
    private String city;

    @NotBlank(message = "Informe uma sigla para o estado!")
    @Size(min = 2, max = 2, message = "Sigla inválida!")
    @JsonProperty(value = "UF")
    private String UF;

    @NotBlank(message = "Informe um país!")
    @JsonProperty(value = "country")
    private String country;

    public String toString() {
        return "Código Postal: "+this.zipCode+" | Rua: "+this.street+" | Numero: "+this.number+" | Complemento: "+this.complement+" | Bairro: "+this.neighborhood+" | Cidade: "+this.city+" | UF: "+this.UF+" | País: "+this.country;
    }
}
