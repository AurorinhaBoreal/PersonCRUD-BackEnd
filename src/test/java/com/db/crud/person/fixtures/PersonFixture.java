package com.db.crud.person.fixtures;

import java.time.LocalDate;

import com.db.crud.person.dto.RequestPersonDTO;
import com.db.crud.person.entity.Person;

public class PersonFixture {
    
    public static RequestPersonDTO PersonDTOValidFixture() {
        return new RequestPersonDTO("Aurora", "Rossi", "37491502814", LocalDate.of(2004, 05, 14));
    }

    public static RequestPersonDTO PersonDTOInvalidFixture() {
        return new RequestPersonDTO("", "", "67951675008", null);
    }

    public static Person PersonEntityValidFixture() {
        Person person = new Person(PersonDTOValidFixture());
        person.setId(1000L);

        return person;
    }

    public static Person PersonEntityInvalidFixture() {
        Person person = new Person(PersonDTOInvalidFixture());

        return person;
    }
}
