package com.db.crud.person.controller;

import org.springframework.web.bind.annotation.RestController;

import com.db.crud.person.dto.AddressDTO;
import com.db.crud.person.entity.Address;
import com.db.crud.person.service.AddressService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/address")
@Slf4j
public class AddressController {    

    @Autowired
    private AddressService addressService;
    
    @GetMapping
    public ResponseEntity<List<Address>> listAddress() {
        var body = addressService.list();

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @PostMapping("/create/{personID}")
    public ResponseEntity<Address> createAddress(@RequestBody @Valid AddressDTO address, @PathVariable Long personID) {

        var body = addressService.create(address, personID);

        log.info("O Corpo do endereço: \\n"+address);

        return ResponseEntity.status(HttpStatus.CREATED).body(body);
        
    }

    @PatchMapping("/update/{addressID}")
    public ResponseEntity<Address> updateAddress(@RequestBody @Valid AddressDTO address, @PathVariable Long addressID) {

        var body = addressService.update(address, addressID);
        
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
    
    @DeleteMapping("/delete/{addressID}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressID) {
        
        addressService.delete(addressID);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
