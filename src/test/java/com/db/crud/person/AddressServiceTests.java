package com.db.crud.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.db.crud.person.dto.AddressDTO;
import com.db.crud.person.dto.PersonDTO;
import com.db.crud.person.entity.Address;
import com.db.crud.person.entity.Person;
import com.db.crud.person.exception.CreateAddressException;
import com.db.crud.person.exception.DeleteAddressException;
import com.db.crud.person.exception.UpdateAddressException;
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

    PersonDTO personDTO;
    Person person;
    AddressDTO addressDTO;
    Address address;
    @BeforeEach
    void addressSetup() {
        personDTO = new PersonDTO("Aurora", "Kruschewsky", "37491502814", LocalDate.of(2004, 05, 14));
        person = new Person(personDTO);
        addressDTO = new AddressDTO("06453225","Estrada Ferreira Silva","143","Apt 5A","Vizinhança Curiosa","Osasco","SP","Brasil", false);
        address = new Address(addressDTO);
        person.setID(111L);
        address.setID(111L);
    }

    @Test
    @DisplayName("Happy Testt: Should list addresses")
    void listAddress() {
        when(addressRepository.findAll()).thenReturn(List.of(address));

        List<Address> addresses = addressService.list();

        assertEquals(1, addresses.size());
    }

    @Test
    @DisplayName("Happy Test: Should Create Address Assigned to a Person")
    void createAddress() {
        when(personRepository.findById(person.getID())).thenReturn(Optional.of(person));
        when(addressRepository.save(any())).thenReturn(address);

        Address addressCreated = addressService.create(addressDTO, person.getID());

        assertNotNull(addressCreated.getPersonID());
        assertEquals(addressDTO.getCity(), address.getCity());
        assertEquals(addressDTO.getNeighborhood(), address.getNeighborhood());
        assertEquals(addressDTO.isMainAddress(), address.isMainAddress());

        // when(addressService.assignAddress(address, anyLong())).thenReturn(address);
    }

    @Test
    @DisplayName("Sad Test: Should thrown CreateAddressException of create")
    void thrownCreateAddressException() {
        CreateAddressException thrown = assertThrows(CreateAddressException.class, () -> {
            when(personRepository.findById(person.getID())).thenReturn(Optional.empty());
            addressService.create(addressDTO, person.getID());
        });
        
        assertEquals("Não foi possivel criar o endereço!", thrown.getMessage());
    }

    @Test
    @DisplayName("Sad Test: Should thrown CreateAddressException of assignAddress")
    void thrownAssignAddressException() {
        CreateAddressException thrown = assertThrows(CreateAddressException.class, () -> {
            addressService.assignAddress(address, null);
        });
        
        assertEquals("Não foi possivel vincular o endereço a pessoa!", thrown.getMessage());
    }

    @Test
    @DisplayName("Happy Test: Should Verify if Person has two main Address")
    void verifyMainAddress() {
        when(personRepository.findById(person.getID())).thenReturn(Optional.of(person));
        when(addressService.create(addressDTO, person.getID()));

        List<Address> addresses = person.getAddress();
        address.setMainAddress(true);
        addresses.add(address);
        addresses.add(address);
        

        addressService.verifyMainAddress(address, person.getID());

        assertNotNull(addresses);
    }

    @Test
    @DisplayName("Sad Test: Should thrown CreateAddressException of verifyMainAddress")
    void thrownVerifyMainAddressException() {
        CreateAddressException thrown = assertThrows(CreateAddressException.class, () -> {
            

        });
        
        assertEquals("", thrown.getMessage());
    }

    @Test
    @DisplayName("Happy Test: Should Update the Address in the Database")
    void updateAddress() {
    
    }

    @Test
    @DisplayName("Sad Test: Should thrown UpdateAddressException of update")
    void thrownUpdateAddressException() {
        UpdateAddressException thrown = assertThrows(UpdateAddressException.class, () -> {
            // Test Logic
        });
        
        assertEquals("", thrown.getMessage());
    }

    @Test
    @DisplayName("Happy Test: Should delete the Address from the database")
    void deleteAddress() {
        
    }

    @Test
    @DisplayName("Sad Test:")
    void thrownDeleteAddressException() {
        DeleteAddressException thrown = assertThrows(DeleteAddressException.class, () -> {
            // Test Logic
        });
        
        assertEquals("", thrown.getMessage());
    }
}
