package com.db.crud.person.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.crud.person.dto.mapper.AddressMapper;
import com.db.crud.person.dto.request.AddressRequest;
import com.db.crud.person.dto.response.AddressResponse;
import com.db.crud.person.entity.Address;
import com.db.crud.person.entity.Person;
import com.db.crud.person.exception.AddressNotFoundException;
import com.db.crud.person.exception.DuplicateMainAddressException;
import com.db.crud.person.repository.AddressRepository;
import com.db.crud.person.repository.PersonRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AddressService {
    
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    public List<Address> list() {
        return addressRepository.findAll();
    }

    @Transactional
    public AddressResponse create(AddressRequest addressDTO, String personCpf) {

        Address address = AddressMapper.dtoToAddress(addressDTO);
        assignAddress(address, personCpf);

        addressRepository.save(address);
        AddressResponse responseAddress = AddressMapper.INSTANCE.addressToDto(address);
        return responseAddress;
    }

    public Address assignAddress(Address address, String personCpf) {
        Person person = personService.findPerson(personCpf);
        
        if (address.isMainAddress()) {
            verifyDuplicateMainAddress(person, address.isMainAddress());
        }
        
        person.setHasMainAddress(true);
        address.setPersonId(person);
        return address;
    }

    

    @Transactional
    public AddressResponse update(AddressRequest addressUpdate, Long addressId) {
        Address addressOriginal = findAddress(addressId);

        addressOriginal.setZipCode(addressUpdate.zipCode());
        addressOriginal.setStreet(addressUpdate.street());
        addressOriginal.setNumber(addressUpdate.number());
        addressOriginal.setNeighborhood(addressUpdate.neighborhood());
        addressOriginal.setCity(addressUpdate.city());
        addressOriginal.setUf(addressUpdate.uf());
        addressOriginal.setCountry(addressUpdate.country());
        addressOriginal.setMainAddress(addressUpdate.mainAddress());
        
        updateMainAddress(addressOriginal);
        
        addressRepository.save(addressOriginal);        

        log.info("The address is the main Address? "+addressOriginal.isMainAddress());
        AddressResponse responseAddress = AddressMapper.INSTANCE.addressToDto(addressOriginal);
        return responseAddress;
    }

    public void delete(Long addressId) {
        Address address = findAddress(addressId);
        
        deleteMainAddress(address);
        addressRepository.delete(address);

        log.info("The Address was deleted. Id: "+addressId);
    }

    public void verifyDuplicateMainAddress(Person person, boolean isMainAddress) {
        
        boolean mainValidation = addressRepository.existsByPersonIdAndMainAddress(person, isMainAddress);
            if (mainValidation) {
                throw new DuplicateMainAddressException("A Main Address Vinculated with this person already exists!");
            }
    }

    public void deleteMainAddress(Address address) {
        Person person = address.getPersonId();
        
        if (address.isMainAddress()) {
            person.setHasMainAddress(false);
        }
    }
    
    public void updateMainAddress(Address address) {
        Person person = address.getPersonId();

        if (address.isMainAddress()) {
            person.setHasMainAddress(true);
        } else {
            person.setHasMainAddress(false);
        }
    }

    public Address findAddress(Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(
            () -> new AddressNotFoundException("No Address found with ID: " + addressId));;
        return address;
    }
}
