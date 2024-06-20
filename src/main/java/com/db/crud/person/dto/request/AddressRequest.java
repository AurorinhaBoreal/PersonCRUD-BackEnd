package com.db.crud.person.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record AddressRequest(
    @NotNull(message = "Must inform a Identifier")
    Long addressIdentifier,

    @NotBlank(message = "A ZIP Code must be informed!")
    @Size(min = 7, max = 12, message = "ZIP Code must be valid!")
    String zipCode,

    @NotBlank(message = "A Street must be informed!")
    String street,

    @NotBlank(message = "A number must be informed!")
    String number,

    String complement,

    @NotBlank(message = "A neighborhood must be informed!")
    String neighborhood,

    @NotBlank(message = "A street must be informed!")
    String city,

    @NotBlank(message = "A state abbreviation must be informed!")
    @Size(min = 2, max = 2, message = "Invalid abbreviation!")
    String uf,

    @NotBlank(message = "A country must be informed!")
    String country,

    @NotNull(message = "Specify if is the main address or not!")
    boolean mainAddress
) {
}
