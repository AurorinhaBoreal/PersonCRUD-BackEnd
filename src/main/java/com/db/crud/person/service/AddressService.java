package com.db.crud.person.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.crud.person.controller.PersonController;
import com.db.crud.person.entity.Address;
import com.db.crud.person.exception.CreateAddressException;
import com.db.crud.person.repository.AddressRepository;

@Service
public class AddressService {
    
    private Logger logger = Logger.getLogger(PersonController.class.getName());

    @Autowired
    AddressRepository repository;

    public List<Address> list() {
        return repository.findAll();
    }

    public void create(Address address) {
        try {
            repository.save(address);
            logger.log(Level.INFO, "Endereço criado com sucesso, Endereço: "+address);
        } catch (Exception e) {
            throw new CreateAddressException("Não foi possivel criar o endereço!");
        }
    }

    public void assign(Long personID) {
        try {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
