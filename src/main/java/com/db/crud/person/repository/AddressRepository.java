package com.db.crud.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.crud.person.entity.Address;
import com.db.crud.person.entity.Person;




@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
 
    boolean existsByPersonIdAndMainAddress(Person personId, boolean mainAddress);
    Address findByAddressIdentifier(Long addressIdentifier);
}
