package com.db.crud.person.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity // Define a classe como uma Entidade JPA
@Table(name = "tbl_person") // Define "tbl_person" como o nome da tabela
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Criamos esse construtor somente por causa do JPA
public class Person {
    
    @Id // Indica o atributo como um ID
    @GeneratedValue(strategy=GenerationType.IDENTITY) // Define o tipo de geração do ID
    private Long ID;

    @Column(length=20, nullable = false) // Define o limite de caracteres como 20 e não permite ser null
    private String firstName;

    @Column(length=20, nullable = false)
    private String lastName;

    @Column(length=11, nullable = false)
    private String cpf;

    @Column
    private LocalDate birthDate;

    public Person(String firstName, String lastName, String cpf, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return String.format("PERSON || ID: %d | FirstName: %s | LastName: %s | CPF: %s | BirthDate: %s", 
            ID, firstName, lastName, cpf, birthDate);
    }

    public Long getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCPF() {
        return cpf;
    }
}
