package com.db.crud.person.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.crud.person.dto.AddressDTO;
import com.db.crud.person.entity.Address;
import com.db.crud.person.entity.Person;
import com.db.crud.person.exception.CreateAddressException;
import com.db.crud.person.exception.DeleteAddressException;
import com.db.crud.person.exception.UpdateAddressException;
import com.db.crud.person.repository.AddressRepository;
import com.db.crud.person.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AddressService {
    
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

    public Address update(AddressDTO addressUpdate, Long addressID) {
        try {
            Address addressOriginal = repositoryA.findById(addressID).get();

            addressOriginal.setZipCode(addressUpdate.getZipCode());
            addressOriginal.setStreet(addressUpdate.getStreet());
            addressOriginal.setNumber(addressUpdate.getNumber());
            addressOriginal.setNeighborhood(addressUpdate.getNeighborhood());
            addressOriginal.setCity(addressUpdate.getCity());
            addressOriginal.setUf(addressUpdate.getUf());
            addressOriginal.setCountry(addressUpdate.getCountry());
            addressOriginal.setMainAddress(addressUpdate.isMainAddress());
            repositoryA.save(addressOriginal);

            log.info("O endereço atual é o principal? "+addressOriginal.isMainAddress());

            return addressOriginal;
        } catch (Exception e) {
            throw new UpdateAddressException("Não foi possivel atualizar os dados do Endereço!");
        }
    }

    public void delete(Long addressID) {
        try {
            Address address = repositoryA.findById(addressID).get();
            
            repositoryA.delete(address);

            log.info("O endereço foi deletado. ID: "+addressID);
        } catch (Exception e) {
            throw new DeleteAddressException("Não foi possivel deletar o Endereço!");
        }
    }
}
