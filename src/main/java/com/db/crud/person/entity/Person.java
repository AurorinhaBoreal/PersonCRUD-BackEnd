package com.db.crud.person.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.db.crud.person.dto.PersonDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Define a classe como uma Entidade JPA
@Table(name = "tbl_person") // Define "tbl_person" como o nome da tabela
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Criamos esse construtor somente por causa do JPA
@AllArgsConstructor(access = AccessLevel.PUBLIC) // Cria um construtor para a criação da classe Person com todos os atributos
@Data // Cria automaticamente os getters, setters para todos os atributos e um toString para a classe
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
    @NotNull(message = "Informe uma data válida!")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "personID", cascade = CascadeType.PERSIST) // Identifica a classe pessoa como 1 para muitas em relação a endereço (1:n)
    @Valid // VERIFICAR SE VÁLIDO OS ATRIBUTOS DO ENDEREÇO
    private List<Address> address = new ArrayList<>();


    public Person(PersonDTO person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.cpf = person.getCpf();
        this.birthDate = person.getBirthDate();
        this.address.add(new Address(person.getAddress()));
    }
}
