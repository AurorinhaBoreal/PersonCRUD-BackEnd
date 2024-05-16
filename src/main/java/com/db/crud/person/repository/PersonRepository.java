package com.db.crud.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.crud.person.entity.Person;
import java.util.List;


public interface PersonRepository extends JpaRepository<Person, Long> {
    
    List<Person> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}
