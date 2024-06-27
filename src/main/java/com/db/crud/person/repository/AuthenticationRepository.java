package com.db.crud.person.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.db.crud.person.entity.Authentication;

// Repositorio para a entidade autenticação, permite buscar a mesma por email
@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {

    Optional<Authentication> findByEmail(String email);
    
}
