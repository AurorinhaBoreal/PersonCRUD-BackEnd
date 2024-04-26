package com.db.crud.person.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.db.crud.person.dto.PersonDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity // Define a classe como uma Entidade JPA
@Table(name = "tbl_person") // Define "tbl_person" como o nome da tabela
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Criamos esse construtor somente por causa do JPA
@AllArgsConstructor(access = AccessLevel.PUBLIC) // Cria um construtor para a criação da classe Person com todos os atributos
@Builder // Cria por baixo dos panos construtores separados para cada atributo, facilitando na hora da construção
@Getter // Cria automaticamente os getters para todos os atributos da classe
@Setter // Cria automaticamente os setters para todos os atributos da classe
@ToString
public class Person {
    
    @Id // Indica o atributo como um ID
    @GeneratedValue(strategy=GenerationType.IDENTITY) // Define o tipo de geração do ID
    @Column(name = "person_id")
    private Long ID;

    @Column(length=20, nullable = false) // Define o limite de caracteres como 20 e não permite ser null
    private String firstName;

    @Column(length=20, nullable = false)
    private String lastName;

    @Column(length=11, nullable = false)
    private String cpf;

    @Column
    private LocalDate birthDate;

    @OneToMany(mappedBy = "personID") // Identifica a classe pessoa como 1 para muitas em relação a endereço (1:n)
    private List<Address> address;

    // Por meio do BeanUtils que copia os atributos do PersonBuilder, ele permite fazermos a conversão de DTO pra Entity
    public Person(PersonDTO.PersonDTOBuilder person) {
        BeanUtils.copyProperties(person, this);
    }
}
