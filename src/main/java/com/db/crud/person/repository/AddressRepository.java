package com.db.crud.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.crud.person.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    
}
