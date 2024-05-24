package com.db.crud.person.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.db.crud.person.dto.request.PersonRequest;
import com.db.crud.person.entity.Person;
import com.db.crud.person.fixtures.PersonFixture;
import com.db.crud.person.fixtures.SqlProvider;
import com.db.crud.person.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
public class PersonControllerIntegration {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    PersonRepository personRepository;

    private PersonRequest personDTORequest;
    String json;
    
    @Test
    @DisplayName("Happy Test: Should get Pageable")
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertPerson),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.clearDB)
    })
    void testGetPageable() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/person"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content[1].firstName").value("João"))
        .andExpect(jsonPath("$.content[1].lastName").value("Silva"))
        .andExpect(jsonPath("$.content[1].cpf").value("30143518062"));
    }

    @Test
    @DisplayName("Happy Test: Should Create Person")
    void testCreatePerson() throws Exception {
        
        personDTORequest = PersonFixture.PersonDTOValidFixture();

        json = mapper.writeValueAsString(personDTORequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/person/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.firstName").value(personDTORequest.firstName()));
    }

    @Test
    @DisplayName("Happy Test: Should Update Person")
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertPerson),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.clearDB)
    })
    void testUpdateResponseJSON() throws Exception {
        
        personDTORequest = PersonFixture.PersonDTOValidFixture();
        
        json = mapper.writeValueAsString(personDTORequest);

        String cpf = "30143518062";

        mockMvc.perform(MockMvcRequestBuilders.patch("/person/update/" + cpf)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName").value(personDTORequest.firstName()));

        Person updatePerson = personRepository.findByCpf(personDTORequest.cpf()).get();
        assertEquals("Aurora", updatePerson.getFirstName());
    }

    @Test
    @DisplayName("Happy Test: Should get delete Status ")
    @SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = SqlProvider.insertPerson),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = SqlProvider.clearDB)
    })
    void testDeleteStatus() throws Exception{
        
        String cpf = "30143518062";

        mockMvc.perform(MockMvcRequestBuilders.delete("/person/delete/" + cpf))
            .andExpect(status().isNoContent());
    }
}
