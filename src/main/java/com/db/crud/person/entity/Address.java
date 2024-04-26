package com.db.crud.person.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_adress")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long ID;

    @ManyToOne()
    @JoinColumn(name = "person_id")
    private Person personID;

    @Column(name = "zip_code", length = 10, nullable = false)
    private String zipCode;

    @Column(length = 25, nullable = false)
    private String street;

    @Column(length = 5, nullable = false)
    private String number;

    @Column(length = 20, nullable = false)
    private String neighborhood;

    @Column(length = 20, nullable = false)
    private String city;

    @Column(length = 2, nullable = false)
    private String UF;

    @Column(length = 15, nullable = false)
    private String country;

    public Address(Person personID, String zipCode, String street, String number, String neighborhood, String city, String UF, String country) {
        this.personID = personID;
        this.zipCode =
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this. city = city;
        this.UF = UF;
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format("ADDRESS || ID: %d | PersonID: %d | Street: %s | Number: %s | NeighborHood: %s | City: %s | UF: %s | Country %s", 
            ID, personID, street, number, neighborhood, city, UF, country);
    }

    public Long getID() {
        return ID;
    }

    public Person getPersonID() {
        return personID;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getUF() {
        return UF;
    }

    public String getCountry() {
        return country;
    }
}
