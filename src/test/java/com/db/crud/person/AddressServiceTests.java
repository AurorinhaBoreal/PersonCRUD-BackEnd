package com.db.crud.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.db.crud.person.dto.request.AddressRequest;
import com.db.crud.person.dto.request.PersonRequest;
import com.db.crud.person.dto.response.AddressResponse;
import com.db.crud.person.entity.Address;
import com.db.crud.person.entity.Person;
import com.db.crud.person.exception.DuplicateMainAddressException;
import com.db.crud.person.exception.ObjectNotFoundException;
import com.db.crud.person.fixtures.AddressFixture;
import com.db.crud.person.fixtures.PersonFixture;
import com.db.crud.person.repository.AddressRepository;
import com.db.crud.person.repository.PersonRepository;
import com.db.crud.person.service.AddressService;
import com.db.crud.person.service.PersonService;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTests {
    
    @Mock
    AddressRepository addressRepository;

    @Mock
    PersonRepository personRepository;
    
    @Mock
    PersonService personService;

    @InjectMocks
    AddressService addressService;
    
    AddressRequest addressDTOValid;
    AddressRequest addressDTOInvalid;
	Address addressEntityValid;
	Address addressEntityInvalid;
    PersonRequest personDTOValid;
    Person personEntityValid;

    List<Address> addresses = new ArrayList<>();


    @BeforeEach
	public void personSetup() {
        addressDTOValid = AddressFixture.AddressDTOValidFixture();
		addressDTOInvalid = AddressFixture.AddressDTOInvalidFixture();
        personDTOValid = PersonFixture.PersonDTOValidFixture();

        addressEntityValid = AddressFixture.AddressEntityValidFixture();
		addressEntityInvalid = AddressFixture.AddressEntityInvalidFixture();
        personEntityValid = PersonFixture.PersonEntityValidFixture();
	}


    @Test
    @DisplayName("Happy Testt: Should list addresses")
    void listAddress() {
        when(addressRepository.findAll()).thenReturn(List.of(addressEntityValid));

        List<Address> addresses = addressService.list();

        assertNotNull(addresses);
    }

    @Test
    @DisplayName("Happy Test: Should Create Address Assigned to a Person")
    void createAddress() {
        when(addressRepository.save(any())).thenReturn(addressEntityValid);
        when(personService.findPerson(anyString())).thenReturn(personEntityValid);

        AddressResponse addressCreated = addressService.create(addressDTOValid, personDTOValid.cpf());

        assertNotNull(addressCreated);
        assertEquals(addressDTOValid.city(), addressCreated.city());
        assertEquals(addressDTOValid.neighborhood(), addressCreated.neighborhood());
        assertEquals(addressDTOValid.mainAddress(), addressCreated.mainAddress());
    }

    @Test
    @DisplayName("Sad Test: Should thrown NullPointerException of assignAddress")
    void thrownNullPointerException() {
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
            addressService.assignAddress(addressEntityInvalid, "654564523");
        });
        
        assertEquals(
            "Cannot invoke \"com.db.crud.person.entity.Person.setHasMainAddress(boolean)\" because \"person\" is null", 
            thrown.getMessage());
    }

    @Test
    @DisplayName("Sad Test: Should thrown DuplicateMainAddressException of verifyMainAddress")
    void thrownDuplicateMainAddressException() {
        DuplicateMainAddressException thrown = assertThrows(DuplicateMainAddressException.class, () -> {
            // when(personRepository.findByCpf(personDTOValid.cpf())).thenReturn(Optional.of(personEntityValid));
            when(personService.findPerson(anyString())).thenReturn(personEntityValid);
            when(addressRepository.existsByPersonIdAndMainAddress(personEntityValid, true)).thenReturn(true);

            addressService.create(addressDTOInvalid, "37491502814");
        });
        
        assertEquals("A Main Address Vinculated with this person already exists!", thrown.getMessage());
    }

    @Test
    @DisplayName("Happy Test: Should Update the Address in the Database")
    void updateAddress() {
        when(addressRepository.findById(anyLong())).thenReturn(Optional.of(addressEntityValid));
        addressEntityValid.setPersonId(personEntityValid);

        AddressRequest addressUpdated = new AddressRequest("06453225","Estrada Maneirinha","112","Casa 3","Vizinhança Legau","São Paulo","RJ","Brasil", true);
        addressService.update(addressUpdated, 1000L);

        assertNotNull(addressUpdated);
        assertEquals("Estrada Maneirinha", addressUpdated.street());
    }

    @Test
    @DisplayName("Sad Test: Should thrown UpdateAddressException of update")
    void thrownUpdateAddressException() {
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
            when(addressRepository.findById(anyLong())).thenReturn(Optional.of(addressEntityValid));

            addressService.update(addressDTOInvalid, 1L);
        });
        
        assertEquals(
            "Cannot invoke \"com.db.crud.person.entity.Person.setHasMainAddress(boolean)\" because \"person\" is null", 
            thrown.getMessage());
    }

    @Test
    @DisplayName("Happy Test: Should delete the Address from the database")
    void deleteAddress() {
        when(addressRepository.findById(111L)).thenReturn(Optional.of(addressEntityValid));
        addressEntityValid.setId(111L);
        addressService.delete(addressEntityValid.getId());

        verify(addressRepository, times(1)).delete(addressEntityValid);
    }

    @Test
    @DisplayName("Sad Test: Should thrown ObjectNotFoundException of delete")
    void thrownObjectNotFoundException() {
        ObjectNotFoundException thrown = assertThrows(ObjectNotFoundException.class, () -> {
            addressService.delete(addressEntityInvalid.getId());
        });
        
        assertEquals("No Address found with ID: null", thrown.getMessage());
    }
}
