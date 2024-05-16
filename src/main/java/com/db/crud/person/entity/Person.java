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
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_person")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long ID;

    @Column(length=20, nullable = false)
    private String firstName;

    @Column(length=20, nullable = false)
    private String lastName;

    @Column(length=11, nullable = false)
    private String cpf;

    @Column
    @NotNull(message = "Informe uma data válida!")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "personID", cascade = CascadeType.ALL)
    private List<Address> address = new ArrayList<>();


    public Person(PersonDTO person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.cpf = person.getCpf();
        this.birthDate = person.getBirthDate();
    }
}
