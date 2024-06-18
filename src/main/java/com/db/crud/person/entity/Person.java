package com.db.crud.person.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "tbl_person")
@NoArgsConstructor(access = AccessLevel.PROTECTED) 
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Builder
public class Person {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY) // Define o tipo de geração do Id
    @Column(name = "person_id")
    private Long Id;

    @Column(length=20, nullable = false)
    private String firstName;

    @Column(length=20, nullable = false)
    private String lastName;

    @Column(length=11, nullable = false)
    private String cpf;

    @Column
    @NotNull(message = "Informe uma data válida!")
    private LocalDate birthDate;

    @Column
    private boolean hasMainAddress;

    @Transient
    private Integer age;

    @Column
    private Integer photoId;
    
    @OneToMany(mappedBy = "personId", cascade = CascadeType.ALL)
    @Valid
    final List<Address> address = new ArrayList<>();
}
