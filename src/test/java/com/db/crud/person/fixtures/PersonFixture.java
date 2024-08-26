package com.db.crud.person.fixtures;

import java.time.LocalDate;

import com.db.crud.person.dto.mapper.PersonMapper;
import com.db.crud.person.dto.request.PersonRequest;
import com.db.crud.person.dto.request.UpdatePersonRequest;
import com.db.crud.person.entity.Person;

public class PersonFixture {

    public static UpdatePersonRequest UpdatePersonDTOValid() {
        return new UpdatePersonRequest("Aurora", "Rossi", LocalDate.of(2004, 05, 14), 1);
    }

    public static PersonRequest PersonDTOValidFixture() {
        return new PersonRequest("Aurora", "Rossi", "05708051043", LocalDate.of(2004, 05, 14), 1);
    }

    public static PersonRequest PersonDTOInvalidFixture() {
        return new PersonRequest(null, null, null, LocalDate.of(2004, 05, 14), null);
    }

    public static PersonRequest PersonDTOInvalidAgeFixture() {
        return new PersonRequest("", "", "67951675008", null, null);
    }

    public static Person PersonEntityValidFixture() {
        return PersonMapper.dtoToPerson(PersonDTOValidFixture());
    }

    public static Person PersonEntityInvalidFixture() {
        return PersonMapper.dtoToPerson(PersonDTOInvalidFixture());
    }
}
