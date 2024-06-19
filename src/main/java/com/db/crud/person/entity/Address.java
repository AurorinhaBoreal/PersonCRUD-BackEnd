package com.db.crud.person.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "tbl_address")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long Id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private Person personId;

    @Column(name = "zip_code", length = 12, nullable = false)
    private String zipCode;

    @Column(length = 25, nullable = false)
    private String street;

    @Column(length = 5, nullable = false)
    private String number;

    @Column(length = 30)
    private String complement;
    
    @Column(length = 20, nullable = false)
    private String neighborhood;

    @Column(length = 20, nullable = false)
    private String city;

    @Column(length = 2, nullable = false)
    private String uf;

    @Column(length = 15, nullable = false)
    private String country;

    @Column(nullable = false)
    private boolean mainAddress;
}
