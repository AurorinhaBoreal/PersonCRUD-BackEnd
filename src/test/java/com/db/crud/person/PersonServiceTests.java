package com.db.crud.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.db.crud.person.dto.RequestPersonDTO;
import com.db.crud.person.dto.ResponsePersonDTO;
import com.db.crud.person.entity.Person;
import com.db.crud.person.exception.CreatePersonException;
import com.db.crud.person.exception.DeletePersonException;
import com.db.crud.person.exception.GetInfoException;
import com.db.crud.person.exception.UpdatePersonException;
import com.db.crud.person.repository.PersonRepository;
import com.db.crud.person.service.PersonService;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTests {

	@Mock
	private PersonRepository personRepository;

	@InjectMocks
	private PersonService personService;

    RequestPersonDTO personDTO;
	Person person;

	@BeforeEach
	public void personSetup() {
        personDTO = new RequestPersonDTO("Aurora", "Kruschewsky", "37491502814", LocalDate.of(2004, 05, 14));
		person = new Person(personDTO);
	}
	
	@Test
	@DisplayName("Happy Test: Should find a person in the Database through Id")
	void findById() {

		when(personRepository.findByCpf(anyString())).thenReturn(Optional.of(person)); 

		Person foundPerson = personRepository.findById(1L).get();

		assertNotNull(foundPerson);
	}

	@Test
	@DisplayName("Happy Test: Should find a person in the Database through CPF")
	void findByCpf() {

		when(personRepository.findByCpf(person.getCpf())).thenReturn(Optional.of(person)); 

		Optional<Person> personFound = personRepository.findByCpf(person.getCpf());

		assertNotNull(personFound);
		assertEquals(person.getCpf(), personFound.get().getCpf());
	}
	
	// @Test
	// @DisplayName("Happy Test: Should insert a person in the Database")
	// void createPerson() {

	// 	when(personRepository.save(new Person(personDTO))).thenReturn(person);
    //     // person.setId(1000L);
    //     ResponsePersonDTO createPerson = personService.create(person);

    //     assertEquals(createPerson.firstName(), "Aurora");
    //     assertEquals(createPerson.lastName(), "Kruschewsky");
    //     assertEquals(createPerson.cpf(), "37491502814");
    //     assertEquals(createPerson.birthDate().toString(), "2004-05-14");
	// }

	@Test
	@DisplayName("Sad Test: Should thrown CreatePersonException of create")
	void thrownCreatePersonException() {
		CreatePersonException thrown = assertThrows(CreatePersonException.class, () -> {
			personService.create(null);
		});

		assertEquals("Não foi possivel criar a pessoa!", thrown.getMessage());
	}

	@Test
	@DisplayName("Happy Test: Should update a person in the Database")
	void updatePerson() {
		when(personRepository.findByCpf(anyString())).thenReturn(Optional.of(person));

		RequestPersonDTO personUpdated = new RequestPersonDTO("Roberto", "Pereira", "37491502814", LocalDate.of(2004, 05, 14));
		Person person = new Person(personUpdated);
		person.setId(1L);

		personService.update(personUpdated, person.getCpf());

		assertNotNull(personUpdated);
		assertEquals("Roberto", personUpdated.firstName());
	}

	@Test
	@DisplayName("Sad Test: Should thrown UpdatePersonException of update")
	void thrownUpdatePersonException() {
		UpdatePersonException thrown = assertThrows(UpdatePersonException.class, () -> {
			personService.update(null, null);
		});

		assertEquals("Não foi possivel atualizar os dados de Pessoa", thrown.getMessage());
	}

	@DisplayName("Happy Test: Should delete a specific person")
	@Test
	void deletePessoa() {
  		when(personRepository.findById(person.getId())).thenReturn(Optional.of(person)); 

  		personService.delete(person.getCpf());

  		verify(personRepository, times(1)).delete(person);
	}

	@Test
	@DisplayName("Sad Test: Should thrown DeletePersonException of delete")
	void thrownDeletePersonException() {
		DeletePersonException thrown = assertThrows(DeletePersonException.class, () -> {
			personService.delete(null);
		});

		assertEquals("Não foi possivel deletar a Pessoa!", thrown.getMessage());
	}

	// TODO: FIX PAGEABLE AND THEM FIX THESE TESTS
	// @DisplayName("Happy Test: Should list persons by pagination")
    // @Disabled
	// @Test
    // void findAllPageable() {

    //     List<Person> persons = new ArrayList<>();

    //     Page<Person> personsPage = new PageImpl<>(persons);

    //     when(personRepository.findAll(any(Pageable.class))).thenReturn(personsPage);

    //     Page<Object> personsFound = personService.findAll(Pageable.unpaged());

    //     assertTrue(personsFound.isEmpty());
    // }

	// @Test
	// @Disabled
	// @DisplayName("Sad Test: Should thrown GetInfoException of pagination")
	// void thrownPageableException() {
	// 	GetInfoException thrown = assertThrows(GetInfoException.class, () -> {
	// 		personService.findAll(null);
	// 	});

	// 	assertEquals("Erro ao mostrar páginação!", thrown.getMessage());
	// }

	@Test
	@DisplayName("Happy Test: Should list all person")
	void findAll() {
		when(personRepository.findAll()).thenReturn(List.of(person));

		List<Person> listPersons = personService.list();

		assertFalse(listPersons.isEmpty());
	}

	// @Test
	// @DisplayName("Happy Test: Should inform person's age")
	// void calcAge() {
	// 	when(personRepository.findById(person.getId())).thenReturn(Optional.of(person));

	// 	var info = personService.calcAge(person.getId());

	// 	assertNotNull(info);
	// 	assertEquals("Aurora", info[0]);
	// 	assertNotNull(info[1]);
	// }

	// @Test
	// @DisplayName("Sad Test: Should thrown GetInfoException of calcAge")
	// void thrownCalcAgeException() {		
	// 	GetInfoException thrown = assertThrows(GetInfoException.class, () -> {
	// 		personService.calcAge(person.getId());
	// 	});

	// 	assertEquals("Não foi possivel calcular a idade.", thrown.getMessage());
	// }
}
