package com.db.crud.person.fixtures;

import com.db.crud.person.dto.mapper.AddressMapper;
import com.db.crud.person.dto.request.AddressRequest;
import com.db.crud.person.entity.Address;

public class AddressFixture {
    
    public static AddressRequest AddressDTOValidFixture() {
        return new AddressRequest(11L, "06845270", "Estrada Legal", "100", "Casa 43", "Bairro Assombrado", "Taboão da Serra", "SP", "Brasil", false);
    }

    public static AddressRequest AddressDTOInvalidFixture() {
        return new AddressRequest(null, null, null, null, null, null, null, null, null, true);
    }

    public static Address AddressEntityValidFixture() {
        Address address = AddressMapper.dtoToAddress(AddressDTOValidFixture());
        address.setId(1000L);

        return address;
    }

    public static Address AddressEntityInvalidFixture() {
        Address address = AddressMapper.dtoToAddress(AddressDTOInvalidFixture());

        return address;
    }
}

