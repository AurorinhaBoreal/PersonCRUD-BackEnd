package com.db.crud.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.crud.person.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    
    
}
