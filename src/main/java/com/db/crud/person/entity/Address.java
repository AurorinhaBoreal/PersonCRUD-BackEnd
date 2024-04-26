package com.db.crud.person.entity;

import com.db.crud.person.dto.AddressDTO;

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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_address")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long ID;

    @ManyToOne()
    @JoinColumn(name = "person_id")
    private Person personID;

    @Column(name = "zip_code", length = 12, nullable = false)
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

    public Address(AddressDTO address) {
        // this.personID = address.getPersonID();
        this.zipCode = address.getZipCode();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.neighborhood = address.getNeighborhood();
        this.city = address.getCity();
        this.UF = address.getUF();
        this.country = address.getCountry();
    }

}
