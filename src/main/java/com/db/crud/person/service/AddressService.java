package com.db.crud.person.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.crud.person.dto.RequestAddressDTO;
import com.db.crud.person.dto.ResponseAddressDTO;
import com.db.crud.person.entity.Address;
import com.db.crud.person.entity.Person;
import com.db.crud.person.exception.AddressNotFoundException;
import com.db.crud.person.exception.DuplicateMainAddressException;
import com.db.crud.person.mapper.AddressMapper;
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
    public ResponseAddressDTO create(RequestAddressDTO addressDTO, String personCpf) {

        Address address = new Address(addressDTO);
        assignAddress(address, personCpf);

        if (address.isMainAddress()) verifyCreateMainAddress(address, personCpf);

        addressRepository.save(address);
        ResponseAddressDTO responseAddress = AddressMapper.INSTANCE.addressToDto(address);
        return responseAddress;
    }

    public Address assignAddress(Address address, String personCpf) {
        Person person = personService.findPerson(personCpf);
        
        address.setPersonId(person);
        return address;
    }

    

    @Transactional
    public ResponseAddressDTO update(RequestAddressDTO addressUpdate, Long addressId) {
        Address addressOriginal = findAddress(addressId);

        addressOriginal.setZipCode(addressUpdate.zipCode());
        addressOriginal.setStreet(addressUpdate.street());
        addressOriginal.setNumber(addressUpdate.number());
        addressOriginal.setNeighborhood(addressUpdate.neighborhood());
        addressOriginal.setCity(addressUpdate.city());
        addressOriginal.setUf(addressUpdate.uf());
        addressOriginal.setCountry(addressUpdate.country());
        addressOriginal.setMainAddress(addressUpdate.mainAddress());
        addressRepository.save(addressOriginal);

        log.info("The address is the main Address? "+addressOriginal.isMainAddress());
        ResponseAddressDTO responseAddress = AddressMapper.INSTANCE.addressToDto(addressOriginal);
        return responseAddress;
    }

    public void delete(Long addressId) {
        Address address = findAddress(addressId);
        
        verifyDeleteMainAddress(address);
        addressRepository.delete(address);

        log.info("The Address was deleted. Id: "+addressId);
    }

    public void verifyCreateMainAddress(Address address, String personCpf) {
        Person person = personService.findPerson(personCpf);
        if (person.isHasMainAddress()) throw new DuplicateMainAddressException("A Main Address Vinculated with this person already exists!");
        person.setHasMainAddress(true);
    }

    public void verifyDeleteMainAddress(Address address) {
        if (address.isMainAddress()) {
            Person person = address.getPersonId();
            if (person.isHasMainAddress()) person.setHasMainAddress(false);
        }
    }
    
    public Address findAddress(Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(
            () -> new AddressNotFoundException("No Address found with ID: " + addressId));;
        return address;
    }
}
