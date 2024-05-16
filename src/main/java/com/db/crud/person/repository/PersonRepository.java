package com.db.crud.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.crud.person.entity.Person;
import java.util.Optional;


public interface PersonRepository extends JpaRepository<Person, Long> {
    
    Optional<Person> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}
