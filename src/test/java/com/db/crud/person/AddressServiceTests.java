package com.db.crud.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.db.crud.person.dto.AddressDTO;
import com.db.crud.person.entity.Address;
import com.db.crud.person.repository.AddressRepository;
import com.db.crud.person.service.AddressService;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTests {
    
    @Mock
    AddressRepository addressRepository;

    @InjectMocks
    AddressService addressService;

    AddressDTO addressDTO;
    Address address;
    @BeforeEach
    void addressSetup() {

        addressDTO = new AddressDTO();
        address = new Address(addressDTO);
        address.setID(111L);
    }

    @Test
    @DisplayName("Deve listar os endereços")
    void listAddress() {
        when(addressRepository.findAll()).thenReturn(List.of(address));

        List<Address> addresses = addressService.list();

        assertEquals(1, addresses.size());
    }

    // @Test
    // @DisplayName("Display name")
    // void test() {
    
    // }

    // @Test
    // @DisplayName("Display name")
    // void test() {
    
    // }
}
