package com.db.crud.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.crud.person.entity.Address;
import com.db.crud.person.entity.Person;

public interface AddressRepository extends JpaRepository<Address, Long> {
 
    boolean existsByPersonIdAndMainAddress(Person personId, boolean mainAddress);

}
