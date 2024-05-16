package com.db.crud.person.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.crud.person.dto.RequestAddressDTO;
import com.db.crud.person.dto.ResponseAddressDTO;
import com.db.crud.person.entity.Address;
import com.db.crud.person.entity.Person;
import com.db.crud.person.exception.CreateAddressException;
import com.db.crud.person.exception.DeleteAddressException;
import com.db.crud.person.exception.UpdateAddressException;
import com.db.crud.person.mapper.AddressMapper;
import com.db.crud.person.repository.AddressRepository;
import com.db.crud.person.repository.PersonRepository;

import jakarta.transaction.Transactional;
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

    @Transactional
    public ResponseAddressDTO create(RequestAddressDTO addressDTO, Long personId) {
        try {
            Address address = new Address(addressDTO);
            assignAddress(address, personId);

            if (address.isMainAddress() == true) verifyMainAddress(address, personId);

            repositoryA.save(address);
            ResponseAddressDTO responseAddress = AddressMapper.INSTANCE.addressToDto(address);
            return responseAddress;
        } catch (Exception e) {
            throw new CreateAddressException("Não foi possivel criar o endereço!");
        }
    }

    public Address assignAddress(Address address, Long personId) {
        try {
            Person person = repositoryP.findById(personId).get();
            address.setPersonId(person);
            return address;
        } catch (Exception e) {
            throw new CreateAddressException("Não foi possivel vincular o endereço a pessoa!");
        }
    }

    public void verifyMainAddress(Address address, Long personId) {
        Person person = repositoryP.findById(personId).get();

        List<Address> addresses = person.getAddress();

        addresses.forEach((element) -> {
            if (element.isMainAddress()) {
                throw new CreateAddressException("Ja existe um endereço principal!"
                );
            }
        });
    }

    @Transactional
    public ResponseAddressDTO update(RequestAddressDTO addressUpdate, Long addressId) {
        try {
            Address addressOriginal = repositoryA.findById(addressId).get();

            addressOriginal.setZipCode(addressUpdate.zipCode());
            addressOriginal.setStreet(addressUpdate.street());
            addressOriginal.setNumber(addressUpdate.number());
            addressOriginal.setNeighborhood(addressUpdate.neighborhood());
            addressOriginal.setCity(addressUpdate.city());
            addressOriginal.setUf(addressUpdate.uf());
            addressOriginal.setCountry(addressUpdate.country());
            addressOriginal.setMainAddress(addressUpdate.mainAddress());
            repositoryA.save(addressOriginal);

            log.info("O endereço atual é o principal? "+addressOriginal.isMainAddress());
            ResponseAddressDTO responseAddress = AddressMapper.INSTANCE.addressToDto(addressOriginal);
            return responseAddress;
        } catch (Exception e) {
            throw new UpdateAddressException("Não foi possivel atualizar os dados do Endereço!");
        }
    }

    public void delete(Long addressId) {
        try {
            Address address = repositoryA.findById(addressId).get();
            
            repositoryA.delete(address);

            log.info("The Address was deleted. Id: "+addressId);
        } catch (Exception e) {
            throw new DeleteAddressException("Não foi possivel deletar o Endereço!");
        }
    }
}
