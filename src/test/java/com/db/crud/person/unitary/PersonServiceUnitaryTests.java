package com.db.crud.person.unitary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.db.crud.person.dto.mapper.PersonMapper;
import com.db.crud.person.dto.request.PersonRequest;
import com.db.crud.person.dto.response.PersonResponse;
import com.db.crud.person.entity.Person;
import com.db.crud.person.exception.DuplicateCpfException;
import com.db.crud.person.exception.ObjectNotFoundException;
import com.db.crud.person.fixtures.PersonFixture;
import com.db.crud.person.repository.PersonRepository;
import com.db.crud.person.service.PersonService;

@ExtendWith(MockitoExtension.class)
class PersonServiceUnitaryTests {

	@Mock
	private PersonRepository personRepository;

	@Mock
	private PersonMapper personMapper;

	@InjectMocks
	private PersonService personService;

	PersonRequest personDTOValid = PersonFixture.PersonDTOValidFixture();
	PersonRequest personDTOInvalid = PersonFixture.PersonDTOInvalidFixture();
	PersonRequest personDTOInvalidAge = PersonFixture.PersonDTOInvalidAgeFixture();
	Person personEntityValid = PersonFixture.PersonEntityValidFixture();
	Person personEntityInvalid = PersonFixture.PersonEntityInvalidFixture();

	@Test
	@DisplayName("Happy Test: Should find a person in the Database through id")
	void findById() {

		when(personRepository.findByCpf(personDTOValid.cpf())).thenReturn(Optional.of(personEntityValid));

		Person foundPerson = personRepository.findByCpf(personDTOValid.cpf()).get();

		assertNotNull(foundPerson);
	}

	@Test
	@DisplayName("Happy Test: Should find a person in the Database through CPF")
	void findByCpf() {

		when(personRepository.findByCpf(personDTOValid.cpf())).thenReturn(Optional.of(personEntityValid));

		Optional<Person> personFound = personRepository.findByCpf(personDTOValid.cpf());

		assertNotNull(personFound);
		assertEquals(personDTOValid.cpf(), personFound.get().getCpf());
	}

	@Test
	@DisplayName("Happy Test: Should insert a person in the Database")
	void createPerson() {
		when(personRepository.save(any())).thenReturn(personEntityValid);

		PersonResponse createPerson = personService.create(personDTOValid);

		assertEquals("Aurora", createPerson.firstName());
		assertEquals("Rossi", createPerson.lastName());
		assertEquals("05708051043", createPerson.cpf());
		assertEquals("2004-05-14", createPerson.birthDate().toString());
	}

	@Test
	@DisplayName("Sad Test: Should thrown DuplicateCpfException in create")
	void thrownDuplicateCpfException() {
		when(personRepository.existsByCpf(anyString())).thenReturn(true);

		DuplicateCpfException thrown = assertThrows(DuplicateCpfException.class, () -> {
			personService.create(personDTOValid);
		});

		assertEquals("Already exists a person with this cpf!", thrown.getMessage());
	}

	@Test
	@DisplayName("Sad Test: Should thrown NullPointerException when date is null in person")
	void thrownNullPointerException() {
		NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
			personService.create(personDTOInvalidAge);
		});

		assertEquals(
				"Cannot invoke \"java.time.LocalDate.until(java.time.chrono.ChronoLocalDate)\" because \"startDateInclusive\" is null",
				thrown.getMessage());
	}

	@Test
	@DisplayName("Happy Test: Should update a person in the Database")
	void updatePerson() {
		when(personRepository.findByCpf(personEntityInvalid.getCpf())).thenReturn(Optional.of(personEntityInvalid));

		PersonResponse personUpdated = personService.update(personDTOValid, personEntityInvalid.getCpf());

		assertNotNull(personUpdated);
		assertEquals("Aurora", personUpdated.firstName());
		assertEquals("Rossi", personUpdated.lastName());
		assertEquals(personDTOValid.birthDate(), personUpdated.birthDate());
	}

	@Test
	@DisplayName("Sad Test: Should thrown ObjectNotFoundException of update")
	void thrownUpdateObjectNotFoundException() {
		ObjectNotFoundException thrown = assertThrows(ObjectNotFoundException.class, () -> {
			personService.update(personDTOValid, null);
		});

		assertEquals("No Person Found with this cpf null", thrown.getMessage());
	}

	@Test
	@DisplayName("Happy Test: Should delete a specific person")
	void deletePerson() {
		when(personRepository.findByCpf(personDTOValid.cpf())).thenReturn(Optional.of(personEntityValid));

		personService.delete(personDTOValid.cpf());

		verify(personRepository, times(1)).delete(personEntityValid);
	}

	@Test
	@DisplayName("Sad Test: Should thrown ObjectNotFoundException of delete")
	void thrownDeleteObjectNotFoundException() {
		String cpf = personDTOInvalid.cpf();
		ObjectNotFoundException thrown = assertThrows(ObjectNotFoundException.class, () -> {
			personService.delete(cpf);
		});

		assertEquals("No Person Found with this cpf null", thrown.getMessage());
	}

	@Test
	@DisplayName("Happy Test: Should inform person's age")
	void calcAge() {
		var age = personService.calcAge(personEntityValid);

		assertNotNull(age);
		assertEquals(20, age);
	}
}
