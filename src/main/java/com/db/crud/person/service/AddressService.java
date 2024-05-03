package com.db.crud.person.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.crud.person.controller.PersonController;
import com.db.crud.person.dto.AddressDTO;
import com.db.crud.person.entity.Address;
import com.db.crud.person.entity.Person;
import com.db.crud.person.exception.CreateAddressException;
import com.db.crud.person.repository.AddressRepository;
import com.db.crud.person.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AddressService {
    
    private Logger logger = Logger.getLogger(PersonController.class.getName());

    @Autowired
    AddressRepository repositoryA;

    @Autowired
    PersonRepository repositoryP;

    public List<Address> list() {
        return repositoryA.findAll();
    }

    
    public Address create(AddressDTO addressDTO, Long personID) {
        try {
            Address address = new Address(addressDTO);
            assignAddress(address, personID);

            if (address.isMainAddress() == true) verifyMainAddress(address, personID);

            repositoryA.save(address);
            
            return address;
        } catch (Exception e) {
            throw new CreateAddressException("Não foi possivel criar o endereço!");
        }
    }

    public void assignAddress(Address address, Long personID) {
        try {
            Person person = repositoryP.findById(personID).get();
            address.setPersonID(person);
        } catch (Exception e) {
            throw new CreateAddressException("Não foi possivel vincular o endereço a pessoa!");
        }
    }

    public void verifyMainAddress(Address address, Long personID) {
        Person person = repositoryP.findById(personID).get();

        List<Address> addresses = person.getAddress();

        addresses.forEach((element) -> {
            if (element.isMainAddress() == true) throw new CreateAddressException("Ja existe um endereço principal!");
        });
    }
}
